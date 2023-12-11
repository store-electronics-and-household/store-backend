package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentCategoryId(Long parentCategoryId);

    @Query("select c from Category c ")
    List<Category> findTree();

    Optional<Category> findOneByParentCategoryId(Long parentId);
}
