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
public class ModelImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_image_id")
    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "model_id")
//    private Model modelImage;

    @Column(name = "image_link")
    private String imageLink;
}
