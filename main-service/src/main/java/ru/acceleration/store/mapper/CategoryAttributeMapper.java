package ru.acceleration.store.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.dto.attribute.CategoryAttributeShortDtoResponse;
import ru.acceleration.store.model.CategoryAttribute;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CategoryAttributeMapper {


    CategoryAttributeDtoResponse toCategoryAttributeDtoResponse(CategoryAttributeDtoRequest categoryAttributeDtoRequest);

    @Mapping(source = "attribute", target = "attributeDtoResponse")
    CategoryAttributeDtoResponse toCategoryAttributeDtoResponse(CategoryAttribute categoryAttribute);

    @Mapping(source = "attributeDtoResponse", target = "attribute")
    CategoryAttribute toCategoryAttribute(CategoryAttributeDtoResponse categoryAttributeDtoResponse);

    @Mapping(source = "attribute.name", target = "attributeName")
    CategoryAttributeShortDtoResponse toCategoryAttributeShortDtoResponse(CategoryAttribute categoryAttribute);
}
