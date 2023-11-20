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
@Table(name = "outcome")
@Builder
public class Outcome extends AbstractPersistable<Long> {

//    @JoinColumn(name = "order_id")
//    @ManyToOne
//    Order order;

    @JoinColumn(name = "product_id")
    @ManyToOne
    Product product;
    @Column(name = "price")
    String price;
    @Column(name = "count_outcome")
    String count;
    @JoinColumn(name = "sale_id")
    @OneToOne
    Sale sale;
    @Column(name = "sum_outcome")
    String sum;
}
