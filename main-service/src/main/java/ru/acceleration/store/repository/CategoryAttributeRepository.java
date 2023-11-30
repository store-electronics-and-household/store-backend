package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.CategoryAttribute;

public interface CategoryAttributeRepository extends JpaRepository<CategoryAttribute, Long> {

}
