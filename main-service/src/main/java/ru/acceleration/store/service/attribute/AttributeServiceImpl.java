package ru.acceleration.store.service.attribute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.attribute.*;
import ru.acceleration.store.mapper.ModelAttributeMapper;
import ru.acceleration.store.model.ModelAttribute;
import ru.acceleration.store.repository.ModelAttributeRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttributeServiceImpl implements AttributeService {

    private final ModelAttributeRepository modelAttributeRepository;

    private final ModelAttributeMapper modelAttributeMapper;


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
}
