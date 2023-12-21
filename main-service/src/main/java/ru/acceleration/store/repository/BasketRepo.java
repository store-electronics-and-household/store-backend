package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Basket;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

    @Query(value = "SELECT l FROM Basket l where l.user.id = :userId AND l.basketStatus = 'ACTIVE'")
    Optional<Basket> findBasketByUserIdAndBasketStatusActive(@Param("userId") Long userId);

    @Query(value = "SELECT l FROM Basket l where l.user.id = :userId AND l.id = :basketId AND l.basketStatus = 'SAVED'")
    Optional<Basket> findBasketByIdAndUserIdAndBasketStatusSaved(@Param("userId") Long userId, @Param("basketId") Long basketId);

    @Query(value = "SELECT l FROM Basket l where l.basketStatus = 'SAVED' AND l.user.id = :userId")
    List<Basket> findAllByBasketStatusSavedAndUserId(@Param("userId") Long userId);
}
