package ru.acceleration.store.service.attribute;

import ru.acceleration.store.dto.attribute.ModelAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoResponse;

import java.util.List;

public interface ModelAttributesService {
    List<ModelAttributeDtoResponse> getModelAttributesByModelId(Long modelId);

    ModelAttributeDtoResponse getModelAttributesById(Long modelAttributesId);

    ModelAttributeDtoResponse createModelAttribute(Long modelId, Long categoryAttributeId, ModelAttributeDtoRequest modelAttributeDtoRequest);

    ModelAttributeDtoResponse updateModelAttribute(Long modelAttributeId, Long modelId, Long categoryAttributeId, ModelAttributeDtoRequest modelAttributeDtoRequest);

    void deleteModelAttribute(Long modelAttributeId);
}
