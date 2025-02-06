package com.alten.trial.models;

import com.alten.trial.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODE", unique = true, length = 50)
    private String code;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;


    @Column(name = "IMAGE")
    private String image;

    @Column(name = "CATEGORY", length = 50)
    private String category;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "INTERNAL_REFERENCE", length = 100)
    private String internalReference;

    @Column(name = "SHELL_ID")
    private Long shellId;

    @Enumerated(EnumType.STRING)
    @Column(name = "INVENTORY_STATUS", length = 20)
    private InventoryStatus inventoryStatus;

    @Column(name = "RATING")
    private double rating;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
