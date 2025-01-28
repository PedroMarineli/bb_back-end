package br.com.fatec.burguerboss.order;

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
}
