package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import ru.acceleration.store.dto.attribute.AttributeDtoResponse;
import ru.acceleration.store.model.Attribute;

@Mapper(componentModel = "spring")
public interface AttributeMapper {

    AttributeDtoResponse toAttributeDtoResponse(Attribute attribute);

    Attribute toAttribute(AttributeDtoResponse attributeDtoResponse);
}
