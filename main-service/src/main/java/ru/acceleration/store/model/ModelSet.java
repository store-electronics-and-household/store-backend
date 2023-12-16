package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.*;
import ru.acceleration.store.model.enums.ModelSetStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "model_sets")
@ToString(exclude = {"model"})
public class ModelSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_set_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Column(name = "count")
    @Builder.Default
    private Integer count = 1;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ModelSetStatus modelSetStatus = ModelSetStatus.SomeStatus;

    @ManyToMany
    @JoinTable(name = "model_sets_products_join",
            joinColumns = @JoinColumn(name = "model_set_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;
}

