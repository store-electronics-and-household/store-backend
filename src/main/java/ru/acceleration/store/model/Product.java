package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    private List<Category> categories;

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    private List<Attribute> attributes;

//    private List<Image> images;
}
