package ru.acceleration.store.service.favourite;

import ru.acceleration.store.dto.model.ModelShortDto;

import java.util.List;

public interface FavouriteService {

    void addFavoriteModel(Long modelId, Long userId);

    void deleteFavoriteModel(Long modelId, Long userId);

    List<ModelShortDto> getAllFavorite(Long userId);

}
