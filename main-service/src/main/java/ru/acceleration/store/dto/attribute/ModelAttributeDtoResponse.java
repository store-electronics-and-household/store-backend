package ru.acceleration.store.dto.attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.model.ModelShortDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelAttributeDtoResponse {

    private Long id;

    private ModelShortDto modelShortDto;

    private CategoryAttributeDtoResponse categoryAttributeDtoResponse;

    private String value;
}
