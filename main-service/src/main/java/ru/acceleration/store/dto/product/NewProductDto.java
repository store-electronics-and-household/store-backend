package ru.acceleration.store.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProductDto {

    @NotBlank
    @Size(max = 30)
    private String vendorCode;

    @NotBlank
    @Size(max = 100)
    private String name;
}
