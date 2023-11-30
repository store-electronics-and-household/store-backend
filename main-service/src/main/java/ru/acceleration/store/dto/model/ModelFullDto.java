package ru.acceleration.store.dto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.dto.sale.SaleDto;
import ru.acceleration.store.model.Category;
import ru.acceleration.store.model.ModelAttribute;
import ru.acceleration.store.model.ModelImage;
import ru.acceleration.store.model.Sale;
import ru.acceleration.store.model.enums.ModelStatus;

import java.util.List;

// TODO: описать все необходимые поля для товара
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelFullDto {

    private Long id;

    private String name;

    private String description;

    private Long price;

    private SaleDto sale;

    private CategoryOutcomeDto category;

    private List<ModelImage> modelImages;

    private List<ModelAttribute> modelAttributes;
}
