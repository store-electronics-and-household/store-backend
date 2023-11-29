package ru.acceleration.store.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryOutcomeDto createCategory(CategoryIncomeDto categoryIncomeDto) {
        return null;
    }

    @Override
    public CategoryOutcomeDto findCategoryById(Long id) {
        return null;
    }

    @Override
    public List<CategoryOutcomeDto> findChildCategoriesByParentId(Long id) {
        return null;
    }

    @Override
    public List<CategoryOutcomeDto> findRoots() {
        return null;
    }

    @Override
    public CategoryOutcomeDto updateCategory(CategoryIncomeDto categoryIncomeDto, Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
