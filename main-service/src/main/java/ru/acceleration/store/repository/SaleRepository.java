package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT * FROM sales WHERE promotion_id=:promotionId", nativeQuery = true)
    List<Sale> getSalesByPromotion(Long promotionId);

    @Query(value = "SELECT * FROM sales WHERE product_id=:productId", nativeQuery = true)
    Sale findSaleByProductId(Long productId);
}
