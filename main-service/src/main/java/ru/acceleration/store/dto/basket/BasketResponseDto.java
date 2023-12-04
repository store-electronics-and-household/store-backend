package ru.acceleration.store.dto.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.modelSet.ModelSetResponseDto;
import ru.acceleration.store.model.enums.BasketStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponseDto {

    private Long id;

    private Long userId;

    private LocalDateTime created;

    private BasketStatus basketStatus;

    private List<ModelSetResponseDto> modelSetResponseDtos;
}
