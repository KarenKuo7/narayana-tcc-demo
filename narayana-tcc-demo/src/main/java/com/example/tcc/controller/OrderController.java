package com.example.tcc.controller;

import com.example.tcc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestParam String item, @RequestParam int quantity) {
        orderService.placeOrder(null, item, quantity);
        return "訂單建立成功";
    }
}