package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import ru.acceleration.store.dto.attribute.AttributeDtoRequest;
import ru.acceleration.store.dto.attribute.AttributeDtoResponse;
import ru.acceleration.store.model.Attribute;

@Mapper(componentModel = "spring")
public interface AttributeMapper {

    AttributeDtoResponse toAttributeDtoResponse(AttributeDtoRequest attributeDtoRequest);

    Attribute toAttribute(AttributeDtoResponse attributeDtoResponse);
}
