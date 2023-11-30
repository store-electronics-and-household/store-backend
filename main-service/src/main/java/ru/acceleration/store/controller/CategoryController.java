package ru.acceleration.store.controller;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.service.category.CategoryService;
import ru.acceleration.store.validation.OnCreate;
import ru.acceleration.store.validation.OnUpdate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categories")
    public CategoryOutcomeDto createCategory(@RequestBody @Validated(OnCreate.class) CategoryIncomeDto categoryIncomeDto) {
        CategoryOutcomeDto categoryOutcomeDto = categoryService.createCategory(categoryIncomeDto);
        //add logging
        return categoryOutcomeDto;
    }

    @GetMapping("/categories/{id}")
    public CategoryOutcomeDto findCategoryById(@PathVariable @NotNull @PositiveOrZero Long id) {
        CategoryOutcomeDto categoryOutcomeDto = categoryService.findCategoryById(id);
        //add logging
        return categoryOutcomeDto;
    }

    @GetMapping("/category/{id}")
    public List<CategoryOutcomeDto> findChildCategoriesByParentId(@PathVariable @NotNull @PositiveOrZero Long id) {
        List<CategoryOutcomeDto> categories = categoryService.findChildCategoriesByParentId(id);
        //add logging
        return categories;
    }

    @GetMapping("/categories/roots")
    public List<CategoryOutcomeDto> findRoots() {
        List<CategoryOutcomeDto> categories = categoryService.findRoots();
        //add logging
        return categories;
    }

    @PatchMapping("/categories/{id}")
    public CategoryOutcomeDto updateCategory(@PathVariable @NotNull @PositiveOrZero Long id,
                                             @RequestBody @Validated(OnUpdate.class) CategoryIncomeDto categoryIncomeDto) {
        CategoryOutcomeDto categoryOutcomeDto = categoryService.updateCategory(categoryIncomeDto, id);
        //add logging
        return categoryOutcomeDto;
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategoryById(@NotNull @PositiveOrZero Long id) {
        categoryService.deleteCategoryById(id);
        //add logging
    }
}
