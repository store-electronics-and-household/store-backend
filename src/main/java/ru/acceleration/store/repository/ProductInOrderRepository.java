package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.ProductInOrder;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}