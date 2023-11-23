package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.NewProductDto;
import ru.acceleration.store.dto.ProductFullDto;
import ru.acceleration.store.dto.ProductShortDto;
import ru.acceleration.store.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductShortDto addProduct(@Valid @RequestBody NewProductDto newProductDto) {
        log.info("POST: /products");
        return productService.addProduct(newProductDto);
    }

    @GetMapping("/{productId}")
    public ProductFullDto getProductById(Long productId) {
        log.info("GET: /products/{}", productId);
        return productService.getProductById(productId);
    }
}
