package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "attributes")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_attributes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_type_id")
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

    @ManyToMany
    @JoinTable(name = "product_attributes",
            joinColumns = @JoinColumn(name = "attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
