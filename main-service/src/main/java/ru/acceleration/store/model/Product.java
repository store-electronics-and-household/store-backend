package ru.acceleration.store.model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long id;

    @Column(name = "vendor_code")
    String vendorCode;

    @Column(name = "name")
    String name;

    @Column(name = "category_id")
    Long categoryId;

    @OneToMany
    List<Attribute> attributes = new ArrayList<>();

//    @ManyToMany(mappedBy = "products")
//    @ToString.Exclude
//    private List<Category> categories;
//
//    @ManyToMany(mappedBy = "products")
//    @ToString.Exclude
//    private List<Attribute> attributes;

//    private List<Image> images;
}
