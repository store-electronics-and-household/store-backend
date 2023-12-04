package ru.acceleration.store.dto.modelSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.model.Basket;
import ru.acceleration.store.model.enums.BasketStatus;
import ru.acceleration.store.model.enums.ModelSetStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelSetResponseDto {

    private Long id;

    private ModelShortDto modelShortDto;

    private Integer count;

    private ModelSetStatus modelSetStatus;
}
