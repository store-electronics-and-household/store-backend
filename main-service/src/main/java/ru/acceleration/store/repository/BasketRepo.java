package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Basket;

import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

    @Query(value = "SELECT l FROM Basket l where l.user.id = :userId")
    Optional<Basket> findBasketByUserId(@Param("userId") Long userId);
}
