package ru.acceleration.store.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "type")
    private ModelSetStatus status;

    @OneToMany(mappedBy = "modelSet")
    List<Product> products;
//    @ManyToMany(mappedBy = "productsModels")
//    @ToString.Exclude
//    private List<Category> categories;
//
//    @ManyToMany(mappedBy = "productsModels")
//    @ToString.Exclude
//    private List<Attribute> attributes;

//    private List<Image> images;
}
