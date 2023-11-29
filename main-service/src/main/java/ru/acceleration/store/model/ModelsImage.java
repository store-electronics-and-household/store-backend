package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "model_images")
@NoArgsConstructor
@AllArgsConstructor
public class ModelsImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_image_id")
    private Long id;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
