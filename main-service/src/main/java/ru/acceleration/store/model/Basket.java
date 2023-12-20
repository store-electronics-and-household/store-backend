package ru.acceleration.store.model;
import jakarta.persistence.*;
import lombok.*;
import ru.acceleration.store.model.enums.BasketStatus;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "baskets")
@ToString(exclude = {"modelSets", "user"})
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created")
    @Builder.Default
    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY)
    private List<ModelSet> modelSets;

    @Column(name = "status")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private BasketStatus basketStatus = BasketStatus.ACTIVE;
}
