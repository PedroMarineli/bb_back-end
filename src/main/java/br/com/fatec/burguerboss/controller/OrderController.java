package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.order.*;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<DataListOrder>> listOrders(Pageable pagination){
        return ResponseEntity.ok().body(orderService.listOrders(pagination));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createOrder(@RequestBody DataCreateOrder data){
        orderService.createOrder(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/item")
    @Transactional
    public ResponseEntity<Void> createOrderItem(@RequestBody DataCreateOrderItem data){
        orderService.createOrderItem(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> updateOrder(@RequestBody DataUpdateOrder data){
        orderService.updateOrder(data);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/item")
    @Transactional
    public ResponseEntity<Void> updateOrderItem(@RequestBody DataUpdateOrderItem data){
        orderService.updateOrderItem(data);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id){
        orderService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/finish")
    @Transactional
    public ResponseEntity<Void> finishOrder(@PathVariable Integer id) {
        orderService.finishOrder(id);
        return ResponseEntity.noContent().build();
    }
}
