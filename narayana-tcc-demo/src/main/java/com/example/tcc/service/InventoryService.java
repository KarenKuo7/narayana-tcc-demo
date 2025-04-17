package com.example.tcc.service;

import com.example.tcc.entity.InventoryEntity;
import com.example.tcc.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.lra.annotation.*;
import org.eclipse.microprofile.lra.annotation.ws.rs.LRA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @LRA(value = LRA.Type.REQUIRED)
    @Transactional
    public void tryDeduct(@LRAParam URI lraId, String item, int quantity) {
        InventoryEntity inventory = inventoryRepository.findByItem(item);
        if (inventory.getStock() < quantity) {
            throw new RuntimeException("庫存不足");
        }
        inventory.setStock(inventory.getStock() - quantity);
        inventoryRepository.save(inventory);
    }

    @Complete
    public void confirm(@LRAParam URI lraId) {
        System.out.println("庫存交易確認: " + lraId);
    }

    @Compensate
    public void cancel(@LRAParam URI lraId) {
        System.out.println("庫存交易回滾: " + lraId);
        // 補回庫存行為僅在示範中進行，實際應儲存扣除暫存資料
    }
}