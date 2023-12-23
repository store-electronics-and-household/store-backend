package ru.acceleration.store.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[ а-яА-Яa-zA-Z]*$")
    private String name;

    @NotBlank
    @Pattern(regexp = "^(\\+7( )?)?((\\(\\d{3}\\))|\\d{3})( )?\\d{3}[- ]?\\d{2}[- ]?\\d{2}$")
    private String phone;

    private String deliveryAddress;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future(message = "Дата доставки не может быть в прошлом.")
    private LocalDate deliveryDate;

    private Long deliveryPrice;

    private Long finalPrice;
}
