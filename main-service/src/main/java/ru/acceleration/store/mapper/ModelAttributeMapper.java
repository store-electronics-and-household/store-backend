package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoResponse;
import ru.acceleration.store.model.ModelAttribute;

@Mapper(componentModel = "spring")
public interface ModelAttributeMapper {

    ModelAttributeDtoResponse toModelAttributeDtoResponse(ModelAttributeDtoRequest modelAttributeDtoRequest);

    ModelAttributeDtoResponse toModelAttributeDtoResponse(ModelAttribute modelAttribute);

    ModelAttribute toModelAttribute(ModelAttributeDtoResponse modelAttributeDtoResponse);
}
