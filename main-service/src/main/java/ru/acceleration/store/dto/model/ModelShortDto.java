package ru.acceleration.store.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.ModelsImage;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelShortDto {

    private Long id;

    private String name;

    private Long price;

    List<ModelsImage> modelsImages;
}
