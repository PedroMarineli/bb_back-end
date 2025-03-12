package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.order.*;
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

    @PutMapping
    @Transactional
    public void updateOrder(@RequestBody DataUpdateOrder data){
        orderService.updateOrder(data);
    }

    @PutMapping("/item")
    @Transactional
    public void updateOrderItem(@RequestBody DataUpdateOrderItem data){
        orderService.updateOrderItem(data);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
    }

    @DeleteMapping("/item/{id}")
    public void deleteOrderItem(@PathVariable Integer id){
        orderService.deleteOrderItem(id);
    }
}
