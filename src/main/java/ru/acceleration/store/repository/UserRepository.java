package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    void deleteByUserName(String username);

    Optional<User> findByUserName(String username);

    Optional<User> findByUserNameAndPassword(String username, String password);
}
