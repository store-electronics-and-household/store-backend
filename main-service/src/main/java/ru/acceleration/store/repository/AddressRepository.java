package ru.acceleration.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acceleration.store.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
