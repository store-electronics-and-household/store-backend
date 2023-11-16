package ru.acceleration.store.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "promotions")
@Builder
public class Promotions extends AbstractPersistable<Long> {
    @Column(name = "name_promotions")
    String name;

    @JoinColumn(name = "sale_id")
    @ManyToOne
    Sale sale;

    @JoinColumn(name = "product_id")
    @ManyToOne
    Product product;

}
