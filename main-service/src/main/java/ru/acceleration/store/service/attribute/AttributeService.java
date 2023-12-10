package ru.acceleration.store.service.attribute;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.attribute.*;

import java.util.List;

@Service
public interface AttributeService {


    CategoryAttributeDtoResponse postCategoryAttribute(Long attributeId, Long categoryId,
                                                       CategoryAttributeDtoRequest categoryAttributeDtoRequest);

    ModelAttributeDtoResponse postModelAttribute(Long modelId, Long categoryAttributeId,
                                                 ModelAttributeDtoRequest modelAttributeDtoRequest);


    List<ModelAttributeDtoResponse> getAllModelAttributeByModelId(Long modelId);

    AttributeDtoResponse getAttributeById(Long attributeId);

    AttributeDtoResponse createAttribute(AttributeDtoRequest attributeDtoRequest);

    AttributeDtoResponse updateAttribute(AttributeDtoRequest attributeDtoRequest, Long attributeId);

    void deleteAttribute(Long attributeId);

    List<AttributeDtoResponse> findAttributes(String text, int from, int size);

//    AttributeCategoryResponse getAttributeCategory(AttributeCategoryRequest dto);
//
//    AttributeProductResponse getAttributeProduct(AttributeProductRequest dto);
}
