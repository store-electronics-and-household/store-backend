package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductAttributesRepository extends JpaRepository<ProductAttributes, Long> {

    List<ProductAttributes> findAllByProductId(Long productId);

    List<ProductAttributes> findAllByProductIn(List<Long> idsProduct);

    @Query("SELECT p " +
            "FROM ProductAttributes p " +
            "WHERE p.value IN (SELECT DISTINCT pa.value FROM ProductAttributes pa WHERE pa.product.id IN ?1)")
    List<ProductAttributes> findAllAttributeCategory(List<Long> idsProduct);
}
