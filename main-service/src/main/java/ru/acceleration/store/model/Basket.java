package ru.acceleration.store.model;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.acceleration.store.model.enums.BasketStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baskets")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_basket_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created")
    private LocalDateTime created;

    @OneToMany
    @JoinColumn(name = "basket_id")
    private List<ModelSet> productModelSets;

    @Column(name = "status")
    private BasketStatus basketStatus;
}
