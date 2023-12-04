package ru.acceleration.store.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoResponse;
import ru.acceleration.store.model.ModelAttribute;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryAttributeMapper.class, CategoryMapper.class})
public interface ModelAttributeMapper {

    @Mapping(source = "categoryAttribute", target = "categoryAttributeDtoResponse")
    @Mapping(source = "model", target = "modelShortDto")
    ModelAttributeDtoResponse toModelAttributeDtoResponse(ModelAttribute modelAttribute);

    @Mapping(source = "categoryAttribute", target = "categoryAttributeDtoResponse")
    @Mapping(source = "model", target = "modelShortDto")
    List<ModelAttributeDtoResponse> toModelAttributeDtoResponseList(List<ModelAttribute> modelAttributes);
}
