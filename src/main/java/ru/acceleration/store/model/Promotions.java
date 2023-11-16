package ru.acceleration.store.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Column(name = "sale_id")
    @ManyToOne
    Sale sale;

    @Column(name = "product_id")
    @ManyToOne
    Product product;

}
