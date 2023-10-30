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
public class Product {

    // товар

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Идентификатор товара
    @ManyToOne
    private Brand brand;   // Идентификатор товара
    private String name;   // Название товара
    private String description;   // Описание товара
    private String category;   // категория товара  (в дальнейшем Enum или сущность)
    private double price;   // стоимость товара
    private double weight;   // Вес товара
    private double width ;   // ширина товара
    private double height ;   // длинна  товара
    private double length;   // выстоа товара

    // а так же куча других параметров, уточним их у PM

}
