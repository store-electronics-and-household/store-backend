package ru.acceleration.store.service.model;

import org.springframework.data.domain.Page;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.enums.ModelSort;

import java.util.Comparator;

public interface ModelService {

    ModelShortDto addModel(Long categoryId, NewModelDto newModelDto);

    ModelFullDto getModelById(Long modelId);

    Model getExistingModel(Long modelId);

    Page<ModelShortDto> getModelByCategory(Long categoryId, Integer from, Integer size, String sort);

    Comparator<Model> getComparator(ModelSort sort);

    Page<ModelShortDto> searchModels(String text, Integer from, Integer size, String sort);
}
