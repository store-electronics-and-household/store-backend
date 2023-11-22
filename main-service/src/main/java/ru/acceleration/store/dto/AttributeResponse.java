package ru.acceleration.store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.acceleration.store.model.ProductAttributes;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AttributeResponse {

    List<ProductAttributes> attributes;
}
