package com.store.service;

import com.store.entity.Product;
import com.store.entity.ProductType;
import com.store.entity.User;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getByProductTypeId(Long productTypeId) {
        return productRepository.findByProductTypeId(productTypeId);
    }

    public Map<String, List<Product>> getAllByProductType() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(p -> p.getProductType().getName()));
    }

    public List<Product> getType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
