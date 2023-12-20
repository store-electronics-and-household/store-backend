package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.image.ImageDto;
import ru.acceleration.store.dto.image.NewImageDto;
import ru.acceleration.store.model.ModelImage;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {

    ModelImage toImage(NewImageDto newImageDto);

    @Mapping(source = "model.id", target = "modelId")
    ImageDto toImageDto(ModelImage image);
}
