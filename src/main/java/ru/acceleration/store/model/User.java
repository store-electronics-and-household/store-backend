package ru.acceleration.store.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    // Пользователь

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "name")
    String name;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "telephone_number")
    Long telephoneNumber;

    @ManyToOne()
    @JoinColumn(name = "address_id")
    Address address;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @Column(name = "registration_status")
    Boolean registrationStatus;

    @ManyToOne()
    @JoinColumn(name = "bank_card_id")
    BankCard bankCard;
}
