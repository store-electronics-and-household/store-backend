package ru.acceleration.store.service.attribute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.attribute.*;
import ru.acceleration.store.mapper.AttributeProductMapper;
import ru.acceleration.store.repository.ProductAttributesRepository;
import ru.acceleration.store.service.model.ModelService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttributeServiceImpl implements AttributeService {

    private final ProductAttributesRepository productAttributesRepository;

    private final ModelService modelService;

    @Override
    public AttributeCategoryResponse getAttributeCategory(AttributeCategoryRequest dto) {
        log.info("AttributeServiceImpl getAttributeCategory dto {}", dto);
        List<Long> productListId = modelService.getProductIdByCategory(dto.getCategoryId());
        List<ProductAttributes> attributeList = productAttributesRepository.findAllAttributeCategory(productListId);
        AttributeCategoryResponse attributeCategoryResponse = AttributeProductMapper.toAttributeCategoryResponse(attributeList);
        return attributeCategoryResponse;
    }

    @Override
    public AttributeProductResponse getAttributeProduct(AttributeProductRequest dto) {
        log.info("AttributeServiceImpl getAttributeProduct dto {}", dto);
        List<ProductAttributes> attributeList = productAttributesRepository.findAllByProductId(dto.getProductId());
        List<ProductAttributesDto> productAttributesDtos = attributeList.stream()
                .map(AttributeProductMapper::toProductAttributesDto)
                .toList();
        return AttributeProductResponse.builder()
                .attributes(productAttributesDtos)
                .build();
    }
}
