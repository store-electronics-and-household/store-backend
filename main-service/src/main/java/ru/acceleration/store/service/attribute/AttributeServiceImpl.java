package ru.acceleration.store.service.attribute;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.acceleration.store.dto.attribute.*;
import ru.acceleration.store.exceptions.BadRequestException;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.AttributeMapper;
import ru.acceleration.store.mapper.ModelAttributeMapper;
import ru.acceleration.store.model.Attribute;
import ru.acceleration.store.model.ModelAttribute;
import ru.acceleration.store.repository.AttributeRepository;
import ru.acceleration.store.repository.ModelAttributeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttributeServiceImpl implements AttributeService {

    ModelAttributeRepository modelAttributeRepository;

    ModelAttributeMapper modelAttributeMapper;

    AttributeRepository attributeRepository;

    AttributeMapper attributeMapper;

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
        List<ModelAttribute> modelAttributes = modelAttributeRepository.findAllByModelIdOrderByCategoryAttributePriority(modelId);
        List<ModelAttributeDtoResponse> modelAttributeDtoResponses = modelAttributeMapper.toModelAttributeDtoResponseList(modelAttributes);
        return modelAttributeDtoResponses;
    }

    @Override
    public AttributeDtoResponse getAttributeById(Long attributeId) {
        return attributeMapper.toAttributeDtoResponse(attributeRepository.findById(attributeId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Attribute %s is not found", attributeId))));
    }

    @Override
    @Transactional
    public AttributeDtoResponse createAttribute(AttributeDtoRequest attributeDtoRequest) {
        validateAttribute(attributeDtoRequest.getName());
        Attribute attribute = Attribute.builder()
                .name(attributeDtoRequest.getName())
                .build();
        attribute = attributeRepository.save(attribute);
        return attributeMapper.toAttributeDtoResponse(attribute);
    }

    @Override
    @Transactional
    public AttributeDtoResponse patchAttribute(AttributeDtoRequest attributeDtoRequest, Long attributeId) {
        validateAttribute(attributeDtoRequest.getName());
        Attribute attribute = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Attribute %s is not found", attributeId)));
        attribute.setName(attributeDtoRequest.getName());
        attributeRepository.save(attribute);
        return attributeMapper.toAttributeDtoResponse(attribute);
    }

    @Override
    public void deleteAttribute(Long attributeId) {
        Attribute attribute = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Attribute %s is not found", attributeId)));
        attributeRepository.delete(attribute);
    }

    private void validateAttribute(String attributeName) {
        if (attributeName == null || attributeName.isEmpty() || attributeName.length() > 100) {
            throw new BadRequestException("Attribute name is incorrect");
        }
    }
}
