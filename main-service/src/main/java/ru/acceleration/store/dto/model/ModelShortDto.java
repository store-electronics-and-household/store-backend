package ru.acceleration.store.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.image.ImageShortDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelShortDto {

    private Long id;

    private String name;

    private String description;

    private Long price;

    List<ImageShortDto> images;

    private Integer percent;

    private Long oldPrice;
}
