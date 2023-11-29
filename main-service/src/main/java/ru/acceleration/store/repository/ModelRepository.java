package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

}
