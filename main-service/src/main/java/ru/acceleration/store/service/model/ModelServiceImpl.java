package ru.acceleration.store.service.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.ModelMapper;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.repository.ModelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;


    @Override
    public ModelShortDto addModel(NewModelDto newModelDto) {
        ModelShortDto modelShortDto = modelMapper.toModelShortDto(newModelDto);
        return modelMapper.toModelShortDto(modelRepository.save(modelMapper.toModel(modelShortDto)));
    }

    @Override
    public ModelFullDto getModelById(Long modelId) {
        return null;
    }

    @Override
    public List<ModelShortDto> getModelByCategory(Long categoryId, Integer from, Integer size) {
        List<Model> models = modelRepository.findAllByCategoryId(categoryId, PageRequest.of(from, size, Sort.by("id").ascending()));
        List<ModelShortDto> modelShortDtos = modelMapper.toModelShortDtoList(models);
        return modelShortDtos;
    }

    @Override
    public Model getExistingModel(Long modelId) {
        return modelRepository.findById(modelId).orElseThrow(()
                -> new DataNotFoundException(String.format("Model with id=%d was not found", modelId)));
    }
}
