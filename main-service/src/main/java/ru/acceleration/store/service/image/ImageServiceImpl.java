package ru.acceleration.store.service.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.image.ImageDto;
import ru.acceleration.store.dto.image.NewImageDto;
import ru.acceleration.store.mapper.ImageMapper;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.ModelImage;
import ru.acceleration.store.repository.ImageRepository;
import ru.acceleration.store.service.model.ModelService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final ModelService modelService;

    @Override
    public ImageDto uploadImage(NewImageDto newImageDto) {
        Model model = modelService.getExistingModel(newImageDto.getModelId());
        ModelImage modelImage = imageMapper.toImage(newImageDto);
        modelImage.setModel(model);
        ModelImage savedImage = imageRepository.save(modelImage);
        log.info("Saved new image for Model with ID: {}", model.getId());

        return imageMapper.toImageDto(savedImage);
    }
}
