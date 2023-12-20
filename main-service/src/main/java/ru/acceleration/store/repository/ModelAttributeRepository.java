package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.ModelAttribute;

import java.util.List;

@Repository
public interface ModelAttributeRepository extends JpaRepository<ModelAttribute, Long> {

    List<ModelAttribute> findAllByModelIdOrderByCategoryAttributePriorityDesc(Long modelId);

    @Query("SELECT ma FROM ModelAttribute ma " +
            "WHERE ma.categoryAttribute.category.id = :categoryId " +
            "AND ma.categoryAttribute.attribute.name = :attributeName")
    List<ModelAttribute> findCategoryAttributeValues(Long categoryId, String attributeName);
}
