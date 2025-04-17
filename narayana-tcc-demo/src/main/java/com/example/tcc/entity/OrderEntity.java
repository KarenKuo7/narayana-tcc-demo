package com.example.tcc.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class OrderEntity {

    @Id
    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "ITEM")
    private String item;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;
}