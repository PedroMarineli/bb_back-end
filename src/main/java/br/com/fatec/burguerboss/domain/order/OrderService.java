package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.desk.Desk;
import br.com.fatec.burguerboss.domain.desk.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private DeskRepository deskRepository;

    public List<DataListOrder> listOrders() {
        return orderRepository.findAll().stream().map(DataListOrder::new).collect(Collectors.toList());
    }

    public void createOrder(DataCreateOrder data) {
        Order order = new Order(data);

        Desk desk = deskRepository.findById(data.desk().getId()).orElse(null);
        assert desk != null;
        desk.setFilled(true);
        deskRepository.save(desk);

        orderRepository.save(order);
    }

    public void createOrderItem(DataCreateOrderItem data) {
        OrderItem orderItem = new OrderItem(data);
        orderItemRepository.save(orderItem);

        updateValue(orderItem.getOrder().getId());
    }

    private void updateValue(Integer id) {
        System.out.println("entrou na funcao de atualizar valor do order");

        Order order = orderRepository.findById(id).orElse(null);
        assert order != null;
        System.out.println("order nao é nulo");

        System.out.println("order escolhido:" + order);
        order.setTotalValue(order.getOrderItems().stream().map(item -> item.getMenuItem().getPrice()).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));

        orderRepository.save(order);
        System.out.println("order salvo");
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
