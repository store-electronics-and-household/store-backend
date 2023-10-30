package ru.acceleration.store.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    // Пользователь

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Идентификатор пользователя

    @Column(name = "USER_FIRST_NAME")
    private String firstName;   // Имя пользователя

    @Column(name = "USER_LAST_NAME")
    private String lastName;   // фамилия пользователя

    @Column(name = "USER_SURNAME")
    private String surname;   // отчество пользователя (хз пока, нужно будет или нет)

    @Column(name = "USER_EMAIL")
    private String email;   // Почта пользователя

    @ManyToOne
    @Column(name = "LOCATION")
    private Location address; // адрес пользователя

    // и прочтии данны, уточнить у PM


}
