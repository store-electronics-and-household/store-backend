package ru.acceleration.store.dto.category;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import ru.acceleration.store.model.enums.CategoryType;
import ru.acceleration.store.validation.OnCreate;
import ru.acceleration.store.validation.OnUpdate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryIncomeDto {
    @NotBlank(groups = OnCreate.class)
    @Size(min = 5, max = 50, groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @PositiveOrZero(groups = {OnCreate.class, OnUpdate.class})
    @Nullable
    private Long parentCategoryId;
}
