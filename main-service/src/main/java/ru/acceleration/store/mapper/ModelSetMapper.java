package ru.acceleration.store.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.modelSet.ModelSetResponseDto;
import ru.acceleration.store.model.ModelSet;

@Mapper(componentModel = "spring", uses = {ModelMapper.class, ProductMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelSetMapper {

    @Mapping(source = "model", target = "modelShortDto")
    @Mapping(source = "products", target = "productResponseDtoList")
    ModelSetResponseDto toModelSetDtoResponse(ModelSet modelSet);

    @Mapping(source = "modelShortDto", target = "model")
    @Mapping(source = "productResponseDtoList", target = "products")
    ModelSet toModelSet(ModelSetResponseDto modelSetResponseDto);
}
