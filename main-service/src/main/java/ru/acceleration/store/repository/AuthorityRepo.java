package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Authority;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {

}
