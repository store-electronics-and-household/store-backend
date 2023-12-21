package ru.acceleration.store.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    @NotBlank
    private String deliveryType;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    private String deliveryAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    private Long deliveryPrice;

    private Long finalPrice;
}
