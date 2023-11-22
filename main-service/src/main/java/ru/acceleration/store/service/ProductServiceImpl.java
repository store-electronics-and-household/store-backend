package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.ProductDto;
import ru.acceleration.store.mapper.ProductMapper;
import ru.acceleration.store.model.Product;
import ru.acceleration.store.repository.ProductRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product newProduct = productRepository.save(productMapper.toProduct(productDto));
        log.info("Created new Product: {}", newProduct);
        return productMapper.toProductDto(newProduct);
    }
}
