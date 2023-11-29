package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.model.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(source = "userId", target = "user.id")
    Category categoryIncomeDtoToCategory(CategoryIncomeDto categoryIncomeDto);

    @Mapping(source = "user.id", target = "userId")
    CategoryOutcomeDto categoryToCategoryOutcomeDto(Category category);
}
