package br.com.fatec.burguerboss.order;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<DataListOrder> listOrders(){
        return orderService.listOrders();
    }

    @PostMapping
    @Transactional
    public void createOrder(@RequestBody DataCreateOrder data){
        orderService.createOrder(data);
    }

    @PostMapping("/item")
    @Transactional
    public void createOrderItem(@RequestBody DataCreateOrderItem data){
        orderService.createOrderItem(data);
    }
}
