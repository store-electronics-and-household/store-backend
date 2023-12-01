package ru.acceleration.store.service.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.dto.category.CategoryShortOutcomeDto;
import ru.acceleration.store.exceptions.BadRequestException;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.CategoryMapper;
import ru.acceleration.store.model.Category;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.repository.CategoryRepository;
import ru.acceleration.store.repository.ModelRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelRepository modelRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryOutcomeDto createCategory(CategoryIncomeDto categoryIncomeDto) {
        Long parentId = categoryIncomeDto.getParentCategoryId();
        assertCategoryHasNoModels(parentId);
        Category parentCategory = null;
        if (parentId != null) {
            parentCategory = categoryRepository.findById(parentId)
                    .orElseThrow(() -> new DataNotFoundException("Category with id: " + parentId + " not found."));
        }
        Category category = categoryMapper.categoryIncomeDtoToCategory(categoryIncomeDto, parentCategory);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryOutcomeDto(category);
    }

    @Override
    public CategoryOutcomeDto findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category with id: " + id + " not found."));
        return categoryMapper.categoryToCategoryOutcomeDto(category);
    }

    @Override
    public List<CategoryShortOutcomeDto> findChildCategoriesByParentId(Long id) {
        return categoryRepository.findAllByParentCategoryId(id)
                .stream()
                .map(categoryMapper::categoryToCategoryShortOutcomeDto)
                .sorted(Comparator.comparingLong(CategoryShortOutcomeDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryShortOutcomeDto> findRoots() {
        return categoryRepository.findAllByParentCategoryId(null)
                .stream()
                .map(categoryMapper::categoryToCategoryShortOutcomeDto)
                .sorted(Comparator.comparingLong(CategoryShortOutcomeDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryOutcomeDto updateCategory(CategoryIncomeDto categoryIncomeDto, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category with id: " + id + " not found."));

        Long newParentId = categoryIncomeDto.getParentCategoryId();
        if (newParentId != null) {
            assertCategoryHasNoModels(newParentId);
            Category parentCategory = categoryRepository.findById(newParentId)
                    .orElseThrow(() -> new DataNotFoundException("Category with id: " + id + " not found."));
            category.setParentCategory(parentCategory);
        }

        String name = categoryIncomeDto.getName();
        if (name != null) {
            category.setName(name);
        }

        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryOutcomeDto(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        assertCategoryHasNoModels(id);
        assertCategoryHasNoChilds(id);
        categoryRepository.deleteById(id);
    }

    private void assertCategoryHasNoModels(Long categoryId) {
        if (categoryId == null) {
            return;
        }
        Optional<Model> model = modelRepository.findOneByCategoryId(categoryId);
        if (model.isPresent()) {
            throw new BadRequestException("Parent category with id: " + categoryId + " not empty. Please, remove all models.");
        }
    }

    private void assertCategoryHasNoChilds(Long categoryId) {
        if (categoryId == null) {
            return;
        }
        Optional<Category> child = categoryRepository.findOneByParentCategoryId(categoryId);
        if (child.isPresent()) {
            throw new BadRequestException("Parent category with id: " + categoryId + " not empty. Please, remove all child categories.");
        }
    }
}
