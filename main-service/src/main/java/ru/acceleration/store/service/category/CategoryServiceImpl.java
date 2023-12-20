package ru.acceleration.store.service.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.dto.category.CategoryShortOutcomeDto;
import ru.acceleration.store.exceptions.BadRequestException;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.CategoryMapperImpl;
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
    private final CategoryMapperImpl categoryMapper;

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
        Optional<Category> childCategory = categoryRepository.findOneByParentCategoryId(id);

        return categoryMapper.categoryToCategoryOutcomeDto(category, childCategory.isEmpty());
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
    public CategoryOutcomeDto updateCategory(CategoryIncomeDto categoryIncomeDto,
                                             Long id,
                                             boolean removeParent,
                                             boolean removeImageLink) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category with id: " + id + " not found."));

        updateParent(category, categoryIncomeDto, removeParent);
        updateName(category, categoryIncomeDto);
        updateImageLink(category, categoryIncomeDto, removeImageLink);

        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryOutcomeDto(category);
    }

    private void updateImageLink(Category category, CategoryIncomeDto categoryIncomeDto, boolean removeImageLink) {
        if (categoryIncomeDto.getImageLink() != null && removeImageLink) {
            throw new BadRequestException("Image link in body and request parameter are not consistent.");
        }
        String imageLink = categoryIncomeDto.getImageLink();
        if (imageLink != null) {
            category.setImageLink(imageLink);
        } else if (removeImageLink) {
            category.setImageLink(null);
        }
    }

    private void updateName(Category category, CategoryIncomeDto categoryIncomeDto) {
        String name = categoryIncomeDto.getName();
        if (name != null) {
            category.setName(name);
        }
    }

    private void updateParent(Category category, CategoryIncomeDto categoryIncomeDto, boolean removeParent) {
        Long newParentId = categoryIncomeDto.getParentCategoryId();
        if (newParentId != null && removeParent) {
            throw new BadRequestException("Parent id in body and request parameter are not consistent.");
        }
        if (newParentId != null) {
            assertCategoryHasNoModels(newParentId);
            Category parentCategory = categoryRepository.findById(newParentId)
                    .orElseThrow(() -> new DataNotFoundException("Parent category with id: " + newParentId + " not found."));
            category.setParentCategory(parentCategory);
        } else if (removeParent) {
            category.setParentCategory(null);
        }
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
