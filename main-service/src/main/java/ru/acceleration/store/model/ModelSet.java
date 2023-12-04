package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.enums.ModelSetStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "model_set")
public class ModelSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_set_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Column(name = "count")
    private Integer count;

    @Column(name = "status")
    private ModelSetStatus status;

    @ManyToMany
    @JoinTable(name = "model_sets_products_join",
            joinColumns = @JoinColumn(name = "model_set_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;
}
