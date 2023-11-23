package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.NewProductDto;
import ru.acceleration.store.dto.ProductShortDto;
import ru.acceleration.store.mapper.ProductMapper;
import ru.acceleration.store.model.Product;
import ru.acceleration.store.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductShortDto addProduct(NewProductDto newProductDto) {
        Product newProduct = productRepository.save(productMapper.toProduct(newProductDto));
        log.info("Created new Product: {}", newProduct);
        return productMapper.toProductShortDto(newProduct);
    }

    @Override
    public List<ProductShortDto> productsInPromotion(Long promotionId) {
        return productRepository.findProductByPromotion(promotionId)
                .stream()
                .map(productMapper::toProductShortDto)
                .collect(Collectors.toList());
    }
}
