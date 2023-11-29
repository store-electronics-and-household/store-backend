package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.exceptions.DataNotFoundException;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    default Promotion getExistingPromotion(Long promotionId) {
        return findById(promotionId).orElseThrow(() -> {
            throw new DataNotFoundException(String.format("Promotion with id=%d was not found", promotionId));
        });
    }
}
