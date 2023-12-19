package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.Sale;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ModelAttributeMapper.class, CategoryMapperImpl.class})
public interface ModelMapper {

    Model toModel(NewModelDto newModelDto);

    Model toModel(ModelShortDto modelShortDto);

    @Mapping(target = "percent", expression = "java(model.getSale() != null ? model.getSale().getPercent() : null)")
    @Mapping(target = "price", expression = "java(calculateSalePrice(model))")
    @Mapping(target = "oldPrice", expression = "java(model.getSale() != null ? model.getPrice() : null)")
    ModelShortDto toModelShortDto(Model model);

    ModelShortDto toModelShortDto(NewModelDto newModelDto);

    @Mapping(source = "category", target = "categoryShortOutcomeDto")
    ModelFullDto toModelFullDto(Model model);

    @Mapping(source = "categoryShortOutcomeDto", target = "category")
    Model toModel(ModelFullDto modelFullDto);

    List<ModelShortDto> toModelShortDtoList(List<Model> modelList);

    default Long calculateSalePrice(Model model) {
        Sale sale = model.getSale();
        Integer percent = sale != null ? sale.getPercent() : null;

        if (percent == null) {
            return model.getPrice();
        }

        return model.getPrice() - (model.getPrice() * percent / 100);
    }
}
