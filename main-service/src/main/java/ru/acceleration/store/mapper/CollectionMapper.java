package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.collection.CollectionDto;
import ru.acceleration.store.model.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectionMapper {

    CollectionDto toCollectionDto(Collection collection);

    Collection toCollection(CollectionDto collectionDto);
}
