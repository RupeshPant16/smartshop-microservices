package com.smartshop.product_service.service;

import com.smartshop.product_service.entity.Product;
import com.smartshop.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(String productId, Product product) {
        return productRepository.findById(productId).map((existingProduct) -> {
            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            return Optional.of(productRepository.save(existingProduct));
        }).orElse(Optional.empty());
    }

    @Override
    public String DeleteProduct(String productId) {
        if (!productRepository.existsById(productId)) {
            return "Product doesn't exist, Cannot delete";
        }
        productRepository.deleteById(productId);
        return "Deleted Product Successfully!!";
    }
}
