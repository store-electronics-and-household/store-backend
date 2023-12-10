package ru.acceleration.store.service.attribute;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoResponse;
import ru.acceleration.store.exceptions.BadRequestException;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.ModelAttributeMapper;
import ru.acceleration.store.model.CategoryAttribute;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.ModelAttribute;
import ru.acceleration.store.repository.CategoryAttributeRepository;
import ru.acceleration.store.repository.ModelAttributeRepository;
import ru.acceleration.store.repository.ModelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ModelAttributeServiceImpl implements ModelAttributesService {

    ModelAttributeRepository modelAttributeRepository;

    ModelRepository modelRepository;

    ModelAttributeMapper modelAttributeMapper;

    CategoryAttributeRepository categoryAttributeRepository;

    @Override
    public List<ModelAttributeDtoResponse> getModelAttributesByModelId(Long modelId) {
        validateModelId(modelId);
        return modelAttributeRepository.findAllByModelIdOrderByCategoryAttributePriorityDesc(modelId).stream()
                .map(modelAttributeMapper::toModelAttributeDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ModelAttributeDtoResponse getModelAttributesById(Long modelAttributesId) {
        ModelAttribute modelAttribute = validateModelAttribute(modelAttributesId);
        return modelAttributeMapper.toModelAttributeDtoResponse(modelAttribute);
    }

    @Override
    public ModelAttributeDtoResponse createModelAttribute(
            Long modelId, Long categoryAttributeId, ModelAttributeDtoRequest modelAttributeDtoRequest) {
        Model model = validateModelId(modelId);
        CategoryAttribute categoryAttribute = validateCategoryAttribute(categoryAttributeId);
        validateModelAttributeDtoRequest(modelAttributeDtoRequest);
        ModelAttribute modelAttribute = ModelAttribute.builder()
                .model(model)
                .categoryAttribute(categoryAttribute)
                .value(modelAttributeDtoRequest.getValue())
                .build();
        ModelAttribute modelAttributeSaved = modelAttributeRepository.save(modelAttribute);
        return modelAttributeMapper.toModelAttributeDtoResponse(modelAttributeSaved);
    }

    @Override
    public ModelAttributeDtoResponse updateModelAttribute(
            Long modelAttributeId, Long modelId, Long categoryAttributeId, ModelAttributeDtoRequest modelAttributeDtoRequest) {
        ModelAttribute modelAttribute = validateModelAttribute(modelAttributeId);
        Model model = validateModelId(modelId);
        CategoryAttribute categoryAttribute = validateCategoryAttribute(categoryAttributeId);
        validateModelAttributeDtoRequest(modelAttributeDtoRequest);
        modelAttribute.setModel(model);
        modelAttribute.setCategoryAttribute(categoryAttribute);
        modelAttribute.setValue(modelAttribute.getValue());
        ModelAttribute modelAttributeSaved = modelAttributeRepository.save(modelAttribute);
        return modelAttributeMapper.toModelAttributeDtoResponse(modelAttributeSaved);
    }

    @Override
    public void deleteModelAttribute(Long modelAttributeId) {
        validateModelAttribute(modelAttributeId);
        modelAttributeRepository.deleteById(modelAttributeId);
    }

    private Model validateModelId(Long modelId) {
        return modelRepository.findById(modelId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Model id=%s is not found", modelId)));
    }

    private ModelAttribute validateModelAttribute(Long modelAttributeId) {
        return modelAttributeRepository.findById(modelAttributeId)
                .orElseThrow(() -> new DataNotFoundException(String.format("ModelAttribute id=%s is not found", modelAttributeId)));
    }

    private CategoryAttribute validateCategoryAttribute(Long categoryAttributeId) {
        return categoryAttributeRepository.findById(categoryAttributeId)
                .orElseThrow(() -> new DataNotFoundException(String.format("CategoryAttribute id=%s is not found", categoryAttributeId)));
    }

    private void validateModelAttributeDtoRequest(ModelAttributeDtoRequest modelAttributeDtoRequest) {
        String value = modelAttributeDtoRequest.getValue();
        if (value == null || value.isEmpty() || value.length() > 250) {
            throw new BadRequestException("ModelAttribute value is not correct");
        }
    }
}
