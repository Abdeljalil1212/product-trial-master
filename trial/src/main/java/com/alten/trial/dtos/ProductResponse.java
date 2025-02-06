package com.alten.trial.dtos;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId;
    private String inventoryStatus;
    private double rating;
    private String createdAt;
    private String updatedAt;
}
