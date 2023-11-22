package ru.acceleration.store.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.acceleration.store.model.Attribute;
import ru.acceleration.store.model.ProductAttributes;

import java.util.List;

public interface ProductAttributesRepository extends JpaRepository<ProductAttributes, Long> {

    List<ProductAttributes> findAllByProductId(Long productId);
}
