package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.*;
import ru.acceleration.store.model.Attribute;
import ru.acceleration.store.model.ProductAttributes;
import ru.acceleration.store.repository.AttributeRepository;
import ru.acceleration.store.repository.ProductAttributesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttributeServiceImpl implements AttributeService {

    private final ProductAttributesRepository productAttributesRepository;

    AttributeRepository attributeRepository;

    @Override
    public AttributeResponse getAttributeCategory(AttributeCategoryRequest dto){
        return null;
    }

    @Override
    public AttributeResponse getAttributeProduct(AttributeProductRequest dto){
        log.info("AttributeServiceImpl getAttributeProduct dto {}", dto);
        List<ProductAttributes> attributeList= productAttributesRepository.findAllByProductId(dto.getProductId());
        return AttributeResponse.builder()
                .attributes(attributeList)
                .build();
    }
}
