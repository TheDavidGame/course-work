package com.store.service;

import com.store.entity.ProductType;
import com.store.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    private ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public Iterable<ProductType> getAll() {
        return productTypeRepository.findAll();
    }

    public List<ProductType> getType() {
        return (List<ProductType>) productTypeRepository.findAll();
    }
}
