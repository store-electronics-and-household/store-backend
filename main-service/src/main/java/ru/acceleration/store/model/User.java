package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;

    @Column(name = "username", unique = true)
    String username;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone")
    String phone;

    @ManyToMany
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    List<Authority> authorities;

    @ManyToMany
    @JoinTable(name = "user_favourites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "model_id"))
    List<Model> models;


//    @ManyToMany
//    @JoinTable(name = "user_addresses",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "address_id"))
//    List<Address> adresses;
}