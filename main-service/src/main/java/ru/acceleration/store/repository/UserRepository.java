package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
