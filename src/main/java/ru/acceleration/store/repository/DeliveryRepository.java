package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acceleration.store.model.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}