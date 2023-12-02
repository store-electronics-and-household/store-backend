package ru.acceleration.store.service.collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.dto.collection.CollectionDto;
import ru.acceleration.store.dto.collection.UpdateCollectionDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.CollectionMapper;
import ru.acceleration.store.mapper.ModelMapper;
import ru.acceleration.store.model.Collection;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.enums.ModelSort;
import ru.acceleration.store.repository.CollectionRepository;
import ru.acceleration.store.service.model.ModelService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;
    private final ModelMapper modelMapper;
    private final ModelService modelService;

    @Override
    public CollectionDto createCollection(CollectionDto collectionDto) {
        Collection newCollection = collectionRepository.save(collectionMapper.toCollection(collectionDto));
        log.info("Created new Collection: {}", newCollection);
        return collectionMapper.toCollectionDto(newCollection);
    }

    @Transactional
    @Override
    public void deleteCollection(Long collectionId) {
        Collection collection = getExistingCollection(collectionId);
        collectionRepository.delete(collection);
        log.info("Deleted collection with ID: {}", collectionId);
    }

    @Override
    public List<CollectionDto> getCollections() {
        return collectionRepository
                .findAll()
                .stream()
                .map(collectionMapper::toCollectionDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ModelShortDto> getCollection(Long collectionId, String sort) {
        Collection collection = getExistingCollection(collectionId);
        return collection.getModels()
                .stream()
                .sorted(modelService.getComparator(ModelSort.valueOf(sort)))
                .map(modelMapper::toModelShortDto)
                .collect(Collectors.toList());
    }

    /**
     * Метод меняет название и/или ссылку на фотографию у баннера.
     *
     * @param collectionId        Id баннера
     * @param updateCollectionDto Данные для изменения
     * @return измененный банер
     */
    @Override
    public CollectionDto editCollection(Long collectionId, UpdateCollectionDto updateCollectionDto) {
        Collection existingCollection = getExistingCollection(collectionId);
        if (updateCollectionDto.getName() != null) {
            existingCollection.setName(updateCollectionDto.getName());
        }
        if (updateCollectionDto.getImageLink() != null) {
            existingCollection.setImageLink(updateCollectionDto.getImageLink());
        }
        Collection updatedCollection = collectionRepository.save(existingCollection);
        return collectionMapper.toCollectionDto(updatedCollection);
    }

    @Override
    public Collection getExistingCollection(Long collectionId) {
        return collectionRepository.findById(collectionId).orElseThrow(() -> {
            throw new DataNotFoundException(String.format("Collection with id=%d was not found", collectionId));
        });
    }

    @Override
    public void addModelToCollection(Long collectionId, Long modelId) {
        Collection existingCollection = getExistingCollection(collectionId);
        Model existingModel = modelService.getExistingModel(modelId);
        existingCollection.getModels().add(existingModel);
        collectionRepository.save(existingCollection);
        log.info("Added Model with ID: {} to Collection with ID: {}", modelId, collectionId);
    }
}
