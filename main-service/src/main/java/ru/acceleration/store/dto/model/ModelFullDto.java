package ru.acceleration.store.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.attribute.ModelAttributeShortDto;
import ru.acceleration.store.dto.category.CategoryShortOutcomeDto;
import ru.acceleration.store.dto.image.ImageShortDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelFullDto {

    private Long id;

    private String name;

    private String description;

    private Long price;

    private Integer percent;

    private Long oldPrice;

    private CategoryShortOutcomeDto category;

    private List<ImageShortDto> images;

    private List<ModelAttributeShortDto> attributes;
}
