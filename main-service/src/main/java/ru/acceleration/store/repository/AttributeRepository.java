package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}
