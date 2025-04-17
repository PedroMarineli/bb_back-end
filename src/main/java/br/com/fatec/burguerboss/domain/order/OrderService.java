package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.desk.Desk;
import br.com.fatec.burguerboss.domain.desk.DeskRepository;
import br.com.fatec.burguerboss.domain.menu.MenuItem;
import br.com.fatec.burguerboss.domain.menu.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public List<DataListOrder> listOrders() {
        return orderRepository.findAll().stream().map(DataListOrder::new).collect(Collectors.toList());
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
        Order order = orderRepository.findById(data.id()).orElse(null);
        assert order != null;
        order.setOrderStatus(data.orderStatus());
        order.setDesk(data.desk());
        order.setPaymentMethod(data.paymentMethod());
        orderRepository.save(order);
    }

    public void updateOrderItem(DataUpdateOrderItem data) {
        OrderItem item = orderItemRepository.findById(data.id()).orElse(null);
        assert item != null;
        item.setQuantity(data.quantity());
        item.setOrder(data.order());
        item.setMenuItem(data.menuItem());
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public void deleteOrderItem(Integer id) {
        orderItemRepository.deleteById(id);
    }
}
