package ru.acceleration.store.model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    Long id;

    @Column(name = "vendor_code")
    String vendorCode;

    @Column(name = "name")
    String name;

//    @ManyToMany(mappedBy = "products")
//    @ToString.Exclude
//    private List<Category> categories;
//
//    @ManyToMany(mappedBy = "products")
//    @ToString.Exclude
//    private List<Attribute> attributes;

//    private List<Image> images;
}
