package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products_attributes")
public class ProductsAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_attributes_id")
    private Long id;

    @Column(name = "array_id")
    private Long arrayId;

    @ManyToOne
    @Column(name = "attribute_type_id")
    private AttributeType attributeType;

    @Column(name = "value_text")
    private String valueText;

    @Column(name = "value_integer")
    private Integer valueInteger;

    @Column(name = "value_float")
    private Float valueFloat;

    @Column(name = "value_date")
    private LocalDate valueDate;

    @Column(name = "value_blob")
    private byte valueBlob;
}
