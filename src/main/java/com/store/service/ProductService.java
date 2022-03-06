package com.store.service;

import com.store.entity.Product;
import com.store.entity.ProductType;
import com.store.entity.User;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
