package br.com.fatec.burguerboss.domain.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<DataListOrder> listOrders() {
        return orderRepository.findAll().stream().map(DataListOrder::new).collect(Collectors.toList());
    }

    public void createOrder(DataCreateOrder data) {
        Order order = new Order(data);
        orderRepository.save(order);
    }

    public void createOrderItem(DataCreateOrderItem data) {
        OrderItem order = new OrderItem(data);
        orderItemRepository.save(order);
    }

    public void updateOrder(DataUpdateOrder data) {
        Order order = orderRepository.findById(data.id()).orElse(null);
        assert order != null;
        order.setTotalValue(data.totalValue());
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
