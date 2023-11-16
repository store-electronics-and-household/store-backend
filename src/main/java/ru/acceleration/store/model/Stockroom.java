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
@Table(name = "stockroom")
@Builder
public class Stockroom extends AbstractPersistable<Long> {

    @JoinColumn(name = "product_id")
    @ManyToOne
    Product product;
    @Column(name = "count_stockroom")
    String count;

    @Column(name = "reserve")
    String reserve;
}
