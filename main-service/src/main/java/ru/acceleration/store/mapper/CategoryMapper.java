package ru.acceleration.store.mapper;

import org.mapstruct.Mapping;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.dto.category.CategoryShortOutcomeDto;
import ru.acceleration.store.model.Category;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(source = "parentCategory", target = "parentCategory")
    Category categoryIncomeDtoToCategory(CategoryIncomeDto categoryIncomeDto, Category parentCategory);

    CategoryOutcomeDto categoryToCategoryOutcomeDto(Category category);

    CategoryShortOutcomeDto categoryToCategoryShortOutcomeDto(Category category);
}
