package com.example.tcc.service;

import com.example.tcc.entity.Order;
import com.example.tcc.repository.OrderRepository;
import jakarta.transaction.Transactional;

import org.eclipse.microprofile.lra.annotation.Compensate;
import org.eclipse.microprofile.lra.annotation.Complete;
import org.eclipse.microprofile.lra.annotation.ws.rs.LRA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URI;
import com.example.tcc.service.InventoryService;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    @Transactional
    @LRA(value = LRA.Type.REQUIRED)
    public void placeOrder(@LRAParam URI lraId, String item, int quantity) {
        Order order = new Order();
        order.setItem(item);
        order.setQuantity(quantity);
        order.setStatus("PENDING");
        orderRepository.save(order);
        inventoryService.tryDeduct(lraId, item, quantity);
    }

    @Complete
    public void confirm(@LRAParam URI lraId) {
        System.out.println("Confirming LRA: " + lraId);
    }

    @Compensate
    public void cancel(@LRAParam URI lraId) {
        System.out.println("Cancelling LRA: " + lraId);
    }
}