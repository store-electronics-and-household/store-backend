package ru.acceleration.store.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.dto.attribute.CategoryAttributeShortDtoResponse;
import ru.acceleration.store.model.enums.CategoryType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryOutcomeDto {

    private Long id;

    private String name;

    private List<CategoryAttributeShortDtoResponse> categoryAttributes;
}
