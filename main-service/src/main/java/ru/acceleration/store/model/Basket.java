package ru.acceleration.store.model;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_basket")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_basket_id")
    Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

//    @OneToMany(mappedBy = "users_basket")
//    List<Product> product;
    @OneToMany
    @JoinTable(
            name = "basket_products",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;
}
