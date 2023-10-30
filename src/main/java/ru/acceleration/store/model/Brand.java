package ru.acceleration.store.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "BRAND")
public class Brand {

    // бренд

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Идентификатор бренда

    private String name;   // Имя бренда
    private String description;   // Описание бренда

    @ManyToOne
    private User owner;   // владелец бренда

    @ManyToOne
    private Location adress;   // место регистрации бренда

    @Column(name = "USER_EMAIL")
    private String email;   // Почта пользователя

    @ManyToOne
    @Column(name = "LOCATION")
    private Location address; // адрес пользователя

    // и прочтии данны, уточнить у PM

}
