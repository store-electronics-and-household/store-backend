package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.dto.category.CategoryShortOutcomeDto;
import ru.acceleration.store.model.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category categoryIncomeDtoToCategory(CategoryIncomeDto categoryIncomeDto, Category parentCategory);

    CategoryOutcomeDto categoryToCategoryOutcomeDto(Category category);

    CategoryShortOutcomeDto categoryToCategoryShortOutcomeDto(Category category);
}
