package ru.acceleration.store.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryShortTreeOutcomeDto {
    private Long id;
    private String name;
    private List<CategoryShortTreeOutcomeDto> childs;
}
