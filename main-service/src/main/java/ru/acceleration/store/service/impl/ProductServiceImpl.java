package ru.acceleration.store.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.model.Product;
import ru.acceleration.store.repository.ProductRepository;
import ru.acceleration.store.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProductByCategory(Long categoryId) {
        log.info("CategoryAServiceImpl getAllProductByCategory categoryId {}", categoryId);
        List<Product> productList = productRepository.findAllByCategoryId(categoryId);
        return productList;
    }

    ;

    @Override
    public List<Long> getProductIdByCategory(Long categoryId) {
        log.info("CategoryAServiceImpl getProductIdByCategory categoryId {}", categoryId);
        List<Long> productIdList = productRepository.findProducctsIdByCatecory(categoryId);
        return productIdList;
    }

    ;
}
