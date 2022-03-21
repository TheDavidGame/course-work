package com.store.service;

import com.store.entity.ProductType;
import com.store.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> getAll() {
        return productTypeRepository.findAll();
    }

    public ProductType getById(Long id) {
        return productTypeRepository.findById(id).orElseThrow();
    }
}
