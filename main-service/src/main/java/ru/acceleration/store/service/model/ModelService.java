package ru.acceleration.store.service.model;

import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.model.Collection;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.enums.ModelSort;

import java.util.Comparator;
import java.util.List;

public interface ModelService {

    ModelShortDto addModel(NewModelDto newModelDto);

    ModelFullDto getModelById(Long modelId);

    Model getExistingModel(Long modelId);

    List<ModelShortDto> getModelByCategory(Long categoryId, Integer from,  Integer size, String sort);

    Comparator<Model> getComparator(ModelSort sort);
}
