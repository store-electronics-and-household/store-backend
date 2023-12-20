package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.ModelImage;

public interface ImageRepository extends JpaRepository<ModelImage, Long> {
}
