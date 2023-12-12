package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.enums.AttributeType;

@Data
@Entity
@Builder
@Table(name = "category_attributes")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_attribute_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @Column(name = "priority")
    private Long priority;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AttributeType attributeType;
}
