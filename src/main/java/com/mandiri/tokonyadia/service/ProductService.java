package com.mandiri.tokonyadia.service;

import com.mandiri.tokonyadia.entity.Product;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    List<Product> getAllProduct();
    Product getProductById(String productId);
    void deleteProduct(String id);

    List<Product> saveBulkProduct(List<Product> products);
}
