package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.ModelAttribute;

public interface ModelAttributeRepository extends JpaRepository<ModelAttribute, Long> {
}
