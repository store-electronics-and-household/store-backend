package ru.acceleration.store.service.favourite;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.service.user.UserService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final UserService userService;


    public void addFavoriteModel(Long modelId, Long userId){

    };

    public void deleteFavoriteModel(Long modelId, Long userId){

    };

    public List<ModelShortDto> getAllFavorite(Long userId){
        return null;
    };
}
