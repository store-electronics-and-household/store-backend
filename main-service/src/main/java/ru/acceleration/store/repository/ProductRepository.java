package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.product_id, p.vendor_code, p.name, s.sale_id, s.sale_name, s.quantity, s.promotion_id " +
            "FROM products p " +
            "LEFT JOIN sales s ON p.product_id=s.product_id " +
            "WHERE s.promotion_id=:promotionId", nativeQuery = true)
    List<Product> findProductByPromotion(Long promotionId);

    default Product getExistingProduct(Long productId) {
        return findById(productId).orElseThrow(() -> {
            throw new DataNotFoundException(String.format("Product with id=%d was not found", productId));
        });
    }

    List<Product> findAllByCategoryId(Long categoryrId);

    @Query("SELECT p.id " +
            "FROM Product p " +
            "WHERE p.categoryId = ?1")
    List<Long> findProducctsIdByCatecory(Long categoryId);
}
