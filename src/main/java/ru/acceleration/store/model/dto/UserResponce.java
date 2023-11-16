package ru.acceleration.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponce {

    Long id;

    String name;

    String lastName;

    String telephoneNumber;

    String registrationStatus;
}