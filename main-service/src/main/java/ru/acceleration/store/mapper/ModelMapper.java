package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.attribute.ModelAttributeShortDto;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.ModelAttribute;
import ru.acceleration.store.model.Sale;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ModelAttributeMapper.class, CategoryMapperImpl.class})
public interface ModelMapper {

    Model toModel(NewModelDto newModelDto);

    Model toModel(ModelShortDto modelShortDto);

    @Mapping(target = "percent", expression = "java(model.getSale() != null ? model.getSale().getPercent() : null)")
    @Mapping(target = "price", expression = "java(calculateSalePrice(model))")
    @Mapping(target = "oldPrice", expression = "java(model.getSale() != null ? model.getPrice() : null)")
    @Mapping(target = "brand", expression = "java(mapToBrand(model))")
    ModelShortDto toModelShortDto(Model model);

    ModelShortDto toModelShortDto(NewModelDto newModelDto);

    @Mapping(target = "price", expression = "java(calculateSalePrice(model))")
    @Mapping(target = "percent", expression = "java(model.getSale() != null ? model.getSale().getPercent() : null)")
    @Mapping(target = "oldPrice", expression = "java(model.getSale() != null ? model.getPrice() : null)")
    @Mapping(target = "attributes", expression = "java(mapModelAttributesToShortDto(model.getModelAttributes()))")
    ModelFullDto toModelFullDto(Model model);

    default Long calculateSalePrice(Model model) {
        Sale sale = model.getSale();
        Integer percent = sale != null ? sale.getPercent() : null;

        if (percent == null) {
            return model.getPrice();
        }

        return model.getPrice() - (model.getPrice() * percent / 100);
    }

    default List<ModelAttributeShortDto> mapModelAttributesToShortDto(List<ModelAttribute> modelAttributes) {
        return modelAttributes.stream()
                .map(this::mapModelAttributeToShortDto)
                .collect(Collectors.toList());
    }

    default ModelAttributeShortDto mapModelAttributeToShortDto(ModelAttribute modelAttribute) {
        ModelAttributeShortDto dto = new ModelAttributeShortDto();
        dto.setAttributeName(modelAttribute.getCategoryAttribute().getAttribute().getName());
        dto.setValue(modelAttribute.getValue());
        return dto;
    }

    default String mapToBrand(Model model) {
        List<ModelAttribute> modelAttributes = model.getModelAttributes();

        Optional<String> brandAttribute = modelAttributes.stream()
                .filter(attr -> "Бренд".equals(attr.getCategoryAttribute().getAttribute().getName()))
                .map(ModelAttribute::getValue)
                .findFirst();

        return brandAttribute.orElse(null);
    }
}
