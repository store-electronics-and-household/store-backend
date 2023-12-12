package ru.acceleration.store.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.ModelSet;

import java.util.Optional;

@Repository
public interface ModelSetRepository extends JpaRepository<ModelSet, Long> {

//    @Query(value = "SELECT l FROM ModelSet l where l.model.id = :modelId")
//    Optional<ModelSet> findModelSetByProductId(@Param("modelId") Long modelId);

    Optional<ModelSet> findByIdAndBasketId(Long modelSetId, Long basketId);
}
