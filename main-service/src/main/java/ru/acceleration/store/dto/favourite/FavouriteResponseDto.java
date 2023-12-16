package ru.acceleration.store.dto.favourite;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.acceleration.store.dto.model.ModelShortDto;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavouriteResponseDto {

    Set<ModelShortDto> modelShortDtos;
}
