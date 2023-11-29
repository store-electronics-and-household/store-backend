package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

}
