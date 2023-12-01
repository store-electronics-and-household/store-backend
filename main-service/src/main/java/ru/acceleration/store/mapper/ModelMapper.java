package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.Sale;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {

    Model toModel(NewModelDto newModelDto);

    Model toModel(ModelShortDto modelShortDto);

    @Mapping(target = "percent", expression = "java(model.getSale() != null ? model.getSale().getPercent() : null)")
    @Mapping(target = "salePrice", expression = "java(calculateSalePrice(model))")
    ModelShortDto toModelShortDto(Model model);

    ModelShortDto toModelShortDto(NewModelDto newModelDto);

    List<ModelShortDto> toModelShortDtoList(List<Model> modelList);

    default Long calculateSalePrice(Model model) {
        Sale sale = model.getSale();
        Integer percent = sale != null ? sale.getPercent() : null;

        if (percent == null) {
            return null;
        }

        return model.getPrice() - (model.getPrice() * percent / 100);
    }
}
