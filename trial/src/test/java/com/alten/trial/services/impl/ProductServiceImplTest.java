package com.alten.trial.services.impl;

import com.alten.trial.dtos.ProductRequest;
import com.alten.trial.dtos.ProductResponse;
import com.alten.trial.models.Product;
import com.alten.trial.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductRequest productRequest;
    private ProductResponse productResponse;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        productRequest = new ProductRequest();
        productRequest.setCode("product01");
        productRequest.setDescription("produit 1");
        productRequest.setPrice(BigDecimal.valueOf(100));
        productRequest.setName("Product");

        productResponse = new ProductResponse();
        productResponse.setId(1L);
        productResponse.setCode("product01");
        productResponse.setName("Product");

        product = new Product();
        product.setId(1L);
        product.setName("Product");
        product.setCode("product01");
        product.setPrice(BigDecimal.valueOf(100));
        product.setDescription("produit 1");

    }
    @Test
    void testCreateProduct() throws Exception {

        when(objectMapper.convertValue(productRequest, Product.class)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(objectMapper.convertValue(product, ProductResponse.class)).thenReturn(productResponse);


        ProductResponse result = productService.createProduct(productRequest);

        assertNotNull(result);
        assertEquals(productResponse.getId(), result.getId());
        assertEquals(productResponse.getCode(), result.getCode());

        verify(productRepository, times(1)).save(any(Product.class));
    }
    @Test
    void testGetAllProducts() throws Exception {

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        ProductResponse productResponse1 = new ProductResponse();
        productResponse1.setId(1L);
        productResponse1.setName("Product 1");


        List<Product> products = Arrays.asList(product, product1);
        when(productRepository.findAll()).thenReturn(products);
        when(objectMapper.convertValue(product, ProductResponse.class)).thenReturn(productResponse);
        when(objectMapper.convertValue(product1, ProductResponse.class)).thenReturn(productResponse1);

        List<ProductResponse> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product", result.get(0).getName());
        assertEquals("Product 1", result.get(1).getName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() throws Exception {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(objectMapper.convertValue(product, ProductResponse.class)).thenReturn(productResponse);

        ProductResponse result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Product", result.getName());

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(objectMapper.convertValue(productRequest, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(objectMapper.convertValue(product, ProductResponse.class)).thenReturn(productResponse);

        ProductResponse result = productService.updateProduct(1L, productRequest);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Product", result.getName());

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct() throws Exception {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }

}