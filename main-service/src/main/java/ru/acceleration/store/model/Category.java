package ru.acceleration.store.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.enums.CategoryType;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @OneToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<CategoryAttribute> categoryAttributes;
}
