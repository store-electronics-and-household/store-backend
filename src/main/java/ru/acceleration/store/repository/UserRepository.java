package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
