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

    @Column(name = "attribute_arr_id")
    private Long attributeArrId;

    @ManyToMany(mappedBy = "product")
    @ToString.Exclude
    private List<Category> categories;

//    private List<Image> images;

//    @OneToOne
//    private ProductsAttributes attributes;
}
