package ru.acceleration.store.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.modelSet.ModelSetResponseDto;
import ru.acceleration.store.model.ModelSet;

@Mapper(componentModel = "spring", uses = {ModelMapper.class})
public interface ModelSetMapper {

    @Mapping(source = "model", target = "modelShortDto")
    ModelSetResponseDto toModelSetDtoResponse (ModelSet modelSet);

    ModelSet toModelSet(ModelSetResponseDto modelSetResponseDto);
}
