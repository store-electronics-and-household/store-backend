package ru.acceleration.store.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "income")
@Builder
public class Income extends AbstractPersistable<Long> {

    @JoinColumn(name = "product_id")
    @ManyToOne
    Product product;
    @Column(name = "lot_number")
    String lotNumber;
    @Column(name = "income_price")
    String incomePrice;
    @Column(name = "count_income")
    String count;
    @Column(name = "sum_income")
    String sum;

    @Column(name = "income_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime date;
}
