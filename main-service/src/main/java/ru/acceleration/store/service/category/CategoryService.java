package ru.acceleration.store.service.category;

import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;

import java.util.List;

public interface CategoryService {

    CategoryOutcomeDto createCategory(CategoryIncomeDto categoryIncomeDto);

    CategoryOutcomeDto findCategoryById(Long id);

    List<CategoryOutcomeDto> findChildCategoriesByParentId(Long id);

    List<CategoryOutcomeDto> findRoots();

    CategoryOutcomeDto updateCategory(CategoryIncomeDto categoryIncomeDto, Long id);

    void deleteCategoryById(Long id);

}
