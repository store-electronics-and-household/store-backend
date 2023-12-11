package ru.acceleration.store.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.ModelImage;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelShortDto {

    private Long id;

    private String name;

    private String description;

    private Long price;

    private Boolean popular;

    List<ModelImage> modelsImages;

    private Integer percent;

    private Long oldPrice;

    public ModelShortDto(Long id, String name, String description, Long price, List<ModelImage> modelsImages, Integer percent, Long oldPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.modelsImages = modelsImages;
        this.percent = percent;
        this.oldPrice = oldPrice;
    }
}
