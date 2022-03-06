package com.store.repository;

import com.store.entity.Product;
import com.store.entity.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

   List<Product>  findByProductType(ProductType productType);
}
