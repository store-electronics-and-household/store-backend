package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
