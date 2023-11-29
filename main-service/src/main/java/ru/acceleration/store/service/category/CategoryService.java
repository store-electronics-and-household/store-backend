package ru.acceleration.store.service.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
