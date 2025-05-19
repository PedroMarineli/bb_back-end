package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.completedorder.CompletedOrder;
import br.com.fatec.burguerboss.domain.completedorder.CompletedOrderRepository;
import br.com.fatec.burguerboss.domain.desk.Desk;
import br.com.fatec.burguerboss.domain.desk.DeskRepository;
import br.com.fatec.burguerboss.domain.menu.MenuItem;
import br.com.fatec.burguerboss.domain.menu.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private DeskRepository deskRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;
    
    @Autowired
    private CompletedOrderRepository completedOrderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public List<DataListOrder> listOrders() {
        return orderRepository.findAll().stream().map(DataListOrder::new).collect(Collectors.toList());
    }

    public Page<DataListOrder> listOrders(Pageable pagination) {
        return orderRepository.findAll(pagination).map(DataListOrder::new);
    }

    public void createOrder(DataCreateOrder data) {
        try {
            Order order = new Order(data);

            logger.info("Criando novo pedido de id:{}", order.getId());

            Desk desk = deskRepository.findById(data.desk().getId()).orElse(null);
            assert desk != null;
            desk.setFilled(true);
            deskRepository.save(desk);

            orderRepository.save(order);

            logger.info("Salvo o pedido de id:{}", order.getId());
        } catch (Exception e) {
            logger.error("Erro ao criar pedido: {}", e.getMessage());
            throw new RuntimeException("Erro ao criar pedido.", e);
        }
    }

    public void createOrderItem(DataCreateOrderItem data) {
        try {
            logger.info("Criando novo item para o pedido de id:{}", data.order().getId());

            // Carrega as entidades completas do banco de dados
            Order order = orderRepository.findById(data.order().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

            MenuItem menuItem = menuItemRepository.findById(data.menuItem().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Item do menu não encontrado"));

            // Cria o OrderItem com as entidades carregadas
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(data.quantity());
            orderItem.setMenuItem(menuItem);
            orderItem.setOrder(order);

            orderItemRepository.save(orderItem);
            logger.info("Item de id:{} do pedido de id:{} salvo", order.getId(), orderItem.getId());

            updateOrderTotalValue(order.getId());
        } catch (Exception e) {
            logger.error("Erro ao criar item do pedido: {}", e.getMessage());
            throw new RuntimeException("Erro ao criar item do pedido.", e);
        }
    }

    private void updateOrderTotalValue(Integer orderId) {
        try {
            logger.info("Atualizando valor total do pedido de id:{}", orderId);

            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

            // Busca todos os itens do pedido (caso não estejam carregados)
            List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

            BigDecimal totalValue = items.stream()
                    .filter(item -> item.getMenuItem() != null && item.getMenuItem().getPrice() != null)
                    .map(item -> item.getMenuItem().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            order.setTotalValue(totalValue);
            orderRepository.save(order);
            logger.info("Valor total do pedido de id:{} atualizado", orderId);
        } catch (Exception e) {
            logger.error("Erro ao atualizar valor total do pedido: {}", e.getMessage());
            throw new RuntimeException("Erro ao atualizar valor total do pedido.", e);
        }
    }

    public void updateOrder(DataUpdateOrder data) {
        try {
            logger.info("Atualizando pedido com ID: {}", data.id());
            Order order = orderRepository.findById(data.id())
                    .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + data.id()));
        order.setOrderStatus(data.orderStatus());
        order.setDesk(data.desk());
        order.setPaymentMethod(data.paymentMethod());
        orderRepository.save(order);
            logger.info("Pedido com ID: {} atualizado com sucesso", data.id());
        } catch (Exception e) {
            logger.error("Erro ao atualizar pedido com ID {}: {}", data.id(), e.getMessage());
            throw new RuntimeException("Erro ao atualizar pedido.", e);
        }
    }

    public void updateOrderItem(DataUpdateOrderItem data) {
        try {
            logger.info("Atualizando item de pedido com ID: {}", data.id());
            OrderItem item = orderItemRepository.findById(data.id())
                    .orElseThrow(() -> new EntityNotFoundException("Item de pedido não encontrado com o ID: " + data.id()));
            item.setQuantity(data.quantity());
            item.setOrder(data.order());
            item.setMenuItem(data.menuItem());
            orderItemRepository.save(item);

            // Atualizar o valor total do pedido após modificar um item
            updateOrderTotalValue(data.order().getId());
            logger.info("Item de pedido com ID: {} atualizado com sucesso", data.id());
        } catch (Exception e) {
            logger.error("Erro ao atualizar item de pedido com ID {}: {}", data.id(), e.getMessage());
            throw new RuntimeException("Erro ao atualizar item de pedido.", e);
        }
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public void deleteOrderItem(Integer id) {
        orderItemRepository.deleteById(id);
    }

    public void finishOrder(Integer orderId) {
        try {
            logger.info("Finalizando pedido com ID: {}", orderId);
            
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + orderId));
            
            // Verifica se o pedido já está finalizado
            if (order.getOrderStatus() == OrderStatus.FINISHED) {
                logger.warn("Pedido com ID: {} já está finalizado", orderId);
                return;
            }
            
            // Altera o status do pedido para FINISHED
            order.setOrderStatus(OrderStatus.FINISHED);
            orderRepository.save(order);
            
            // Libera a mesa
            Desk desk = order.getDesk();
            desk.setFilled(false);
            deskRepository.save(desk);
            
            // Salva na tabela de pedidos concluídos com os itens
            CompletedOrder completedOrder = new CompletedOrder(order);
            completedOrderRepository.save(completedOrder);
            
            logger.info("Pedido com ID: {} finalizado com sucesso", orderId);
        } catch (Exception e) {
            logger.error("Erro ao finalizar pedido com ID {}: {}", orderId, e.getMessage());
            throw new RuntimeException("Erro ao finalizar pedido.", e);
        }
    }
}
