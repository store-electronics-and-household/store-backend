package ru.acceleration.store.security.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;

    @Column(name = "roles")
    String roles;

    @Column(name = "enabled")
    @Builder.Default
    Boolean enabled = true;
}
