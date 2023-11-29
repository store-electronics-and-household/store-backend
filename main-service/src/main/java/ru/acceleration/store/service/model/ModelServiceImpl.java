package ru.acceleration.store.service.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        return null;
    }

    @Override
    public ModelFullDto getModelById(Long modelId) {
        return null;
    }

    @Override
    public Model getExistingModel(Long modelId) {
        return modelRepository.findById(modelId).orElseThrow(() -> {
            throw new DataNotFoundException(String.format("Model with id=%d was not found", modelId));
        });
    }
}
