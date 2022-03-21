package com.store.api;

import com.store.api.dto.ProductTypeProductsDto;
import com.store.entity.ProductType;
import com.store.service.ProductService;
import com.store.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/productTypes")
@RequiredArgsConstructor
public class ProductTypeApi {

    private final ProductService productService;
    private final ProductTypeService productTypeService;

    @GetMapping
    public List<ProductType> getAll() {
        return productTypeService.getAll();
    }

    @GetMapping("{id}/products")
    public ProductTypeProductsDto getProductsByType(@PathVariable Long id) {
        return ProductTypeProductsDto.builder()
                .name(productTypeService.getById(id).getName())
                .products(productService.getByProductTypeId(id))
                .build();
    }

    @GetMapping("products")
    public List<ProductTypeProductsDto> getAllProducts() {
        return productService.getAllByProductType()
                .entrySet()
                .stream()
                .map(e -> new ProductTypeProductsDto(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
