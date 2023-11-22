package ru.acceleration.store.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @Email
    @NotBlank
    @Column(name = "email", unique = true)
    String email;

    @NotBlank
    @Column(name = "password")
    String password;

    @NotNull
    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone")
    String phone;

//    @ManyToMany
//    @JoinTable(name = "user_addresses",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "address_id"))
//    List<Address> adresses;
}