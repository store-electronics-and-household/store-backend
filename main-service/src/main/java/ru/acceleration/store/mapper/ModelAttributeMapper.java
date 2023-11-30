package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoResponse;
import ru.acceleration.store.model.ModelAttribute;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelAttributeMapper {

    ModelAttributeDtoResponse toModelAttributeDtoResponse(ModelAttributeDtoRequest modelAttributeDtoRequest);

    ModelAttributeDtoResponse toModelAttributeDtoResponse(ModelAttribute modelAttribute);

    ModelAttribute toModelAttribute(ModelAttributeDtoResponse modelAttributeDtoResponse);
}
