package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Category;
import ru.acceleration.store.model.enums.CategoryType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentCategoryId(Long parentCategoryId);
    List<Category> findAllByCategoryType(CategoryType categoryType);
    Optional<Category> findOneByParentCategoryId(Long parentId);
}
