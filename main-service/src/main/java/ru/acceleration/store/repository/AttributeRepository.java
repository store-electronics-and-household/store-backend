package ru.acceleration.store.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Attribute;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    List<Attribute> findByNameContainingIgnoreCase(String name, PageRequest pageRequest);
}