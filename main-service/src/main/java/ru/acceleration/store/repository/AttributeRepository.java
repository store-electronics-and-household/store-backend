package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.Attribute;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}
