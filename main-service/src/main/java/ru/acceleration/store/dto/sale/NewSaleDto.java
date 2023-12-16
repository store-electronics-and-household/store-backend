package ru.acceleration.store.dto.sale;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewSaleDto {

    @NotNull
    @Positive
    private Long modelId;

    @NotNull
    @Positive
    private Integer percent;
}
