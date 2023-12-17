package ru.acceleration.store.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.dto.category.CategoryShortOutcomeDto;
import ru.acceleration.store.service.category.CategoryService;
import ru.acceleration.store.validation.OnCreate;
import ru.acceleration.store.validation.OnUpdate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryOutcomeDto createCategory(@RequestBody @Validated(OnCreate.class) CategoryIncomeDto categoryIncomeDto) {
        CategoryOutcomeDto categoryOutcomeDto = categoryService.createCategory(categoryIncomeDto);
        log.info(String.format("%s : %s : %s", LocalDateTime.now().toString(), "POST /categories", categoryOutcomeDto.toString()));
        return categoryOutcomeDto;
    }

    @GetMapping("/categories/{id}")
    public CategoryOutcomeDto findCategoryById(@PathVariable @NotNull @PositiveOrZero Long id) {
        CategoryOutcomeDto categoryOutcomeDto = categoryService.findCategoryById(id);
        log.info(String.format("%s : %s : %d", LocalDateTime.now(), "GET /categories/{id}", id));
        return categoryOutcomeDto;
    }

    @GetMapping("/categories/{id}/childs")
    public List<CategoryShortOutcomeDto> findChildCategories(@PathVariable @NotNull @PositiveOrZero Long id) {
        List<CategoryShortOutcomeDto> categories = categoryService.findChildCategoriesByParentId(id);
        log.info(String.format("%s : %s : %d", LocalDateTime.now(), "GET /categories/{id}/childs", id));
        return categories;
    }

    @GetMapping("/categories/roots")
    public List<CategoryShortOutcomeDto> findRoots() {
        List<CategoryShortOutcomeDto> categories = categoryService.findRoots();
        log.info(String.format("%s : %s", LocalDateTime.now(), "GET /categories/roots"));
        return categories;
    }

    @PatchMapping("/categories/{id}")
    public CategoryOutcomeDto updateCategory(@PathVariable @NotNull @PositiveOrZero Long id,
                                             @RequestBody @Validated(OnUpdate.class) CategoryIncomeDto categoryIncomeDto) {
        CategoryOutcomeDto categoryOutcomeDto = categoryService.updateCategory(categoryIncomeDto, id);
        log.info(String.format("%s : %s : %d", LocalDateTime.now(), "PATCH /categories/{id}", id));
        return categoryOutcomeDto;
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategoryById(@PathVariable @NotNull @PositiveOrZero Long id) {
        categoryService.deleteCategoryById(id);
        log.info(String.format("%s : %s : %d", LocalDateTime.now(), "DELETE /categories/{id}", id));
    }
}
