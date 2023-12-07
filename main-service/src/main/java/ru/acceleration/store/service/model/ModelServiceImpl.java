package ru.acceleration.store.service.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.acceleration.store.abstraction.PageRequestUtil;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.ModelMapper;
import ru.acceleration.store.model.Category;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.enums.ModelSort;
import ru.acceleration.store.repository.CategoryRepository;
import ru.acceleration.store.repository.ModelRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModelServiceImpl extends PageRequestUtil implements ModelService {

    private final CategoryRepository categoryRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;


    @Override
    public ModelShortDto addModel(Long categoryId, NewModelDto newModelDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()
                -> new DataNotFoundException("category with id: " + categoryId + " not found"));
        ModelShortDto modelShortDto = modelMapper.toModelShortDto(newModelDto);
        Model model = modelMapper.toModel(modelShortDto);
        model.setCategory(category);
        modelRepository.save(model);
        return modelMapper.toModelShortDto(model);
    }

    @Override
    public ModelFullDto getModelById(Long modelId) {
        return null;
    }

    @Override
    public List<ModelShortDto> getModelByCategory(Long categoryId, Integer from, Integer size, String sort) {
        Pageable page = createPageRequest(from, size, ModelSort.valueOf(sort));
        Page<Model> models = modelRepository.findAllByCategoryId(categoryId, page);
        return models.getContent()
                .stream()
                .map(modelMapper::toModelShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public Model getExistingModel(Long modelId) {
        return modelRepository.findById(modelId).orElseThrow(()
                -> new DataNotFoundException(String.format("Model with id=%d was not found", modelId)));
    }

    /**
     * Метод для сортировки при применении сортировки в stream().sorted()
     * {@link ru.acceleration.store.service.collection.CollectionServiceImpl#getCollection}
     *
     * @param sort - вариант сортировки
     * @return Comparator
     */
    @Override
    public Comparator<Model> getComparator(ModelSort sort) {
        return switch (sort) {
            case NAME -> Comparator.comparing(Model::getName);
            case DESC_PRICE -> Comparator.comparing(Model::getPrice).reversed();
            case ASC_PRICE -> Comparator.comparing(Model::getPrice);
            default -> throw new IllegalArgumentException("Unsupported sorting option: " + sort);
        };
    }
}
