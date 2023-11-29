package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "price")
    Long price;

    @Enumerated(EnumType.STRING)
    ModelStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "model")
    List<ModelsImage> modelsImages;

    @OneToMany(mappedBy = "model")
    List<ModelAttribute> modelAttributes;

//    @OneToMany
//    List<Attribute> attributes = new ArrayList<>();

//    @ManyToMany(mappedBy = "productsModels")
//    @ToString.Exclude
//    private List<Category> categories;
//
//    @ManyToMany(mappedBy = "productsModels")
//    @ToString.Exclude
//    private List<Attribute> attributes;

//    private List<Image> images;
}
