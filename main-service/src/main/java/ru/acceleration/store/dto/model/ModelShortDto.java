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

    List<ModelImage> modelsImages;

    private Integer percent;

    private Long salePrice;
}
