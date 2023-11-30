package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.model.CategoryAttribute;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryAttributeMapper {


    CategoryAttributeDtoResponse toCategoryAttributeDtoResponse(CategoryAttributeDtoRequest categoryAttributeDtoRequest);

    CategoryAttributeDtoResponse toCategoryAttributeDtoResponse(CategoryAttribute categoryAttribute);

    CategoryAttribute toCategoryAttribute(CategoryAttributeDtoResponse categoryAttributeDtoResponse);

}
