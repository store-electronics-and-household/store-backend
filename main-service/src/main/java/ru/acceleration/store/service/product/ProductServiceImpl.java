package ru.acceleration.store.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.product.NewProductDto;
import ru.acceleration.store.dto.product.ProductFullDto;
import ru.acceleration.store.dto.product.ProductShortDto;
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
    public ProductFullDto getProductById(Long productId) {
        Product foundProduct = productRepository.getExistingProduct(productId);
        return productMapper.toProductFullDto(foundProduct);
    }

    /**
     * Метод отдает все товары, которые входят в баннер
     *
     * @param promotionId Id баннера
     * @return список товаров, входящих в подборку баннера/акции
     */
    @Override
    public List<ProductShortDto> productsInPromotion(Long promotionId) {
        return productRepository.findProductByPromotion(promotionId)
                .stream()
                .map(productMapper::toProductShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public Product getExistingProduct(Long productId) {
        return productRepository.getExistingProduct(productId);
    }
}
