package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "sales")
@Builder
public class Sale extends AbstractPersistable<Long> {

    @Column(name = "name_sales")
    String name;

    @Column(name = "size_sales")
    Date size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
