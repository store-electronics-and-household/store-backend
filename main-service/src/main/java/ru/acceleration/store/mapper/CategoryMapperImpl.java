package ru.acceleration.store.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.dto.category.CategoryShortOutcomeDto;
import ru.acceleration.store.model.Category;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl {

    private final CategoryAttributeMapper categoryAttributeMapper;

    public Category categoryIncomeDtoToCategory(CategoryIncomeDto categoryIncomeDto, Category parentCategory) {
        Category category = new Category();
        category.setName(categoryIncomeDto.getName());
        category.setParentCategory(parentCategory);
        category.setImageLink(categoryIncomeDto.getImageLink());
        return category;
    }


    public CategoryOutcomeDto categoryToCategoryOutcomeDto(Category category) {
        CategoryOutcomeDto dto = new CategoryOutcomeDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setImageLink(category.getImageLink());
        if (category.getCategoryAttributes() != null) {
            dto.setCategoryAttributes(category.getCategoryAttributes()
                    .stream()
                    .map(categoryAttributeMapper::toCategoryAttributeShortDtoResponse)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public CategoryShortOutcomeDto categoryToCategoryShortOutcomeDto(Category category) {
        CategoryShortOutcomeDto dto = new CategoryShortOutcomeDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setImageLink(category.getImageLink());
        return dto;
    }
}
