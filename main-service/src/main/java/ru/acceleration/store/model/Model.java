package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.acceleration.store.model.enums.ModelStatus;

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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ModelStatus status;

    @OneToOne
    @JoinColumn(name = "model_id")
    @ToString.Exclude
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    @JoinColumn(name = "model_id")
    private List<ModelImage> modelImages;

//    @OneToMany
//    @JoinColumn(name = "model_id")
//    private List<ModelAttribute> modelAttributes;
//
    @OneToMany(mappedBy = "model")
    private List<ModelAttribute> modelAttributes;
}
