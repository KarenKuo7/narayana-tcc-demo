package com.example.tcc.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INVENTORY")
@Getter
@Setter
public class Inventory {

    @Column(name = "ITEM_ID")
    @Id
    private String itemId;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "STOCK")
    private int stock;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

}