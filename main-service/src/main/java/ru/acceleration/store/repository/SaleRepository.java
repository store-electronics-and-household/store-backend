package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Sale;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT * FROM sales WHERE promotion_id=:promotionId", nativeQuery = true)
    List<Sale> getSalesByPromotion(Long promotionId);
}
