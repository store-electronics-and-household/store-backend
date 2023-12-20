package ru.acceleration.store.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.attribute.CategoryAttributeShortDtoResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryOutcomeDto {

    private Long id;

    private String name;

    private Boolean leaf;

    private List<CategoryAttributeShortDtoResponse> categoryAttributes;

    private String imageLink;
}
