package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.attribute.AttributeDtoResponse;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.dto.attribute.CategoryAttributeShortDtoResponse;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.model.Attribute;
import ru.acceleration.store.model.Category;
import ru.acceleration.store.model.CategoryAttribute;

@Mapper(componentModel = "spring")
public interface CategoryAttributeMapper {


    CategoryAttributeDtoResponse toCategoryAttributeDtoResponse(CategoryAttributeDtoRequest categoryAttributeDtoRequest);

    @Mapping(source = "attribute", target = "attributeDtoResponse")
    @Mapping(source = "category", target = "categoryOutcomeDto")
    CategoryAttributeDtoResponse toCategoryAttributeDtoResponse(CategoryAttribute categoryAttribute);

    @Mapping(source = "attributeDtoResponse", target = "attribute")
    @Mapping(source = "categoryOutcomeDto", target = "category")
    CategoryAttribute toCategoryAttribute(CategoryAttributeDtoResponse categoryAttributeDtoResponse);

    @Mapping(source = "attribute.name", target = "attributeName")
    CategoryAttributeShortDtoResponse toCategoryAttributeShortDtoResponse(CategoryAttribute categoryAttribute);

    CategoryOutcomeDto categoryToCategoryOutcomeDto(Category category);

    Category categoryOutcomeDtoToCategory(CategoryOutcomeDto categoryOutcomeDto);

    AttributeDtoResponse attributeToAttributeDtoResponse(Attribute attribute);

    Attribute attributeDtoResponseToAttribute(AttributeDtoResponse attributeDtoResponse);
}
