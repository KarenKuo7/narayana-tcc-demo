package com.example.tcc.repository;

import com.example.tcc.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByItem(String item);
}