package ru.acceleration.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Page<Model> findAllByCategoryId(Long categoryId, Pageable pageable);

    Optional<Model> findOneByCategoryId(Long categoryId);

    @Query(value = "SELECT m FROM Model m " +
                   "WHERE LOWER(m.name) LIKE %:text% " +
                   "OR LOWER(m.category.name) LIKE %:text%")
    Page<Model> searchModels(@Param("text") String text, Pageable pageable);

    List<Model> findAllByCategoryIdAndPopular(Long categoryId, boolean popular);
}
