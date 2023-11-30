package ru.acceleration.store.service.attribute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.attribute.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttributeServiceImpl implements AttributeService {


    @Override
    public CategoryAttributeDtoResponse postCategoryAttribute(Long attributeId, Long categoryId, CategoryAttributeDtoRequest categoryAttributeDtoRequest) {
        return null;
    }

    @Override
    public ModelAttributeDtoResponse postModelAttribute(Long modelId, Long categoryAttributeId, ModelAttributeDtoRequest modelAttributeDtoRequest) {
        return null;
    }

    @Override
    public List<ModelAttributeDtoResponse> getAllModelAttributeByModelId(Long modelId) {
        return null;
    }

//    @Override
//    public AttributeCategoryResponse getAttributeCategory(AttributeCategoryRequest dto) {
//        log.info("AttributeServiceImpl getAttributeCategory dto {}", dto);
//        List<Long> productListId = modelService.getProductIdByCategory(dto.getCategoryId());
//        List<ProductAttributes> attributeList = productAttributesRepository.findAllAttributeCategory(productListId);
//        AttributeCategoryResponse attributeCategoryResponse = AttributeProductMapper.toAttributeCategoryResponse(attributeList);
//        return attributeCategoryResponse;
//    }
//
//    @Override
//    public AttributeProductResponse getAttributeProduct(AttributeProductRequest dto) {
//        log.info("AttributeServiceImpl getAttributeProduct dto {}", dto);
//        List<ProductAttributes> attributeList = productAttributesRepository.findAllByProductId(dto.getProductId());
//        List<ProductAttributesDto> productAttributesDtos = attributeList.stream()
//                .map(AttributeProductMapper::toProductAttributesDto)
//                .toList();
//        return AttributeProductResponse.builder()
//                .attributes(productAttributesDtos)
//                .build();
//    }
}
