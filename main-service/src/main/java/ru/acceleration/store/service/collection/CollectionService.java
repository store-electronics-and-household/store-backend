package ru.acceleration.store.service.collection;

import org.springframework.data.domain.Page;
import ru.acceleration.store.dto.collection.CollectionDto;
import ru.acceleration.store.dto.collection.UpdateCollectionDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.model.Collection;

import java.util.List;

public interface CollectionService {

    CollectionDto createCollection(CollectionDto collectionDto);

    void deleteCollection(Long collectionId);

    List<CollectionDto> getCollections();

    Page<ModelShortDto> getCollection(Long collectionId, String sort, Integer from, Integer size);

    CollectionDto editCollection(Long collectionId, UpdateCollectionDto updateCollectionDto);

    Collection getExistingCollection(Long collectionId);

    void addModelToCollection(Long collectionId, Long modelId);
}
