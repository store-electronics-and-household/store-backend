package ru.acceleration.store.service.image;

import ru.acceleration.store.dto.image.ImageDto;
import ru.acceleration.store.dto.image.NewImageDto;

public interface ImageService {

    ImageDto uploadImage(NewImageDto newImageDto);
}
