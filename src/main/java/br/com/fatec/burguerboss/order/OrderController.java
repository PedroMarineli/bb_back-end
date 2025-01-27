package br.com.fatec.burguerboss.order;

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
    public void createOrder(@RequestBody DataCreateOrder dataCreateOrder){
        orderService.createOrder();
    }
}
