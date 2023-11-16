package ru.acceleration.store.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

//    @Column(name = "product_id")
//    @ManyToOne
//    Product product;
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
