package com.smartshop.product_service.service;

import com.smartshop.product_service.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(String productId);
    Product createProduct(Product product);
    Optional<Product> updateProduct(String productId, Product product);
    String DeleteProduct(String productId);
}
