package ru.acceleration.store.dto.attribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.model.ModelShortDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelAttributeDtoResponse {

    private Long id;

    private ModelShortDto modelShortDto;

    private CategoryAttributeDtoResponse categoryAttributeDtoResponse;

    private String value;
}
