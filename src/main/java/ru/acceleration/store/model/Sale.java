package ru.acceleration.store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

//    @Column(name = "product_id")
//    @ManyToOne
//    Product product;
}
