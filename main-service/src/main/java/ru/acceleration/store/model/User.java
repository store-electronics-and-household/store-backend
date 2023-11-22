package ru.acceleration.store.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Builder
public class User extends AbstractPersistable<Long> {

    @Column(name = "name")
    String name;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "telephone_number")
    String telephoneNumber;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @Column(name = "registration_status")
    String registrationStatus;

    @Column(name = "agreement")
    String agreement;

    @ManyToMany
    @JoinTable(name = "user_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    List<Address> adresses;
}