package com.eestevez.similarproductservice.controller;

import com.eestevez.similarproductservice.model.ProductDetail;
import com.eestevez.similarproductservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}/similar")
    @Operation(summary = "Find similar products")
    public List<ProductDetail> findSimilarProductsById(@PathVariable String id) {
        return productService.findSimilarProducts(id);
    }
}
