package com.alten.trial.services;

import com.alten.trial.dtos.ProductRequest;
import com.alten.trial.dtos.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request) throws Exception;

    List<ProductResponse> getAllProducts() throws Exception;

    ProductResponse getProductById(Long idP) throws Exception;

    ProductResponse updateProduct(Long id, ProductRequest productRequest) throws Exception;

    void deleteProduct(Long id) throws Exception;
}
