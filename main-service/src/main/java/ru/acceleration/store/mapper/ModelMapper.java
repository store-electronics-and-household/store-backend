package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.model.Model;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {

    Model toModel(NewModelDto newModelDto);

    Model toModel(ModelShortDto modelShortDto);

    ModelShortDto toModelShortDto(Model model);

    ModelShortDto toModelShortDto(NewModelDto newModelDto);

    List<ModelShortDto> toModelShortDtoList(List<Model> modelList);
}
