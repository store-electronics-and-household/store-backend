package ru.acceleration.store.service.model;

import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.model.Collection;
import ru.acceleration.store.model.Model;

import java.util.List;

public interface ModelService {

    ModelShortDto addModel(NewModelDto newModelDto);

    ModelFullDto getModelById(Long modelId);

    Model getExistingModel(Long modelId);
}
