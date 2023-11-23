package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.acceleration.store.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryId(Long categoryrId);

    @Query("SELECT p.id " +
            "FROM Product p " +
            "WHERE p.categoryId = ?1")
    List<Long> findProducctsIdByCatecory(Long categoryId);
}
