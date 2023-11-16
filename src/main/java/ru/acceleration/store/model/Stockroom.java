package ru.acceleration.store.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "stockroom")
@Builder
public class Stockroom extends AbstractPersistable<Long> {

//    @Column(name = "product_id")
//    @ManyToOne
//    Product product;
    @Column(name = "count_stockroom")
    String count;

    @Column(name = "reserve")
    String reserve;
}
