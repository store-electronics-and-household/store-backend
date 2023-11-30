package ru.acceleration.store.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.acceleration.store.security.model.Role;
import ru.acceleration.store.security.model.Status;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
  //  @NotBlank(message = "Необходимо указать адрес электронной почты")
  //  @Email(message = "Email должен быть корректным адресом электронной почты")
  //  @Length(min = 6, max = 30, message = "Адрес электронной почты должен содержать от 6 до 30 символов")
    String email;
 //   @NotBlank(message = "Необходимо указать пароль")
 //   @Length(min = 8, max = 40, message = "Пароль должен быть длиной от 8 до 40 символов")
    String password;
 //   @NotBlank(message = "Необходимо повторно указать пароль")
    String confirmPassword;
 //   @NotBlank(message = "Необходимо указать имя")
//    @Length(min = 2, max = 20, message = "Длина имени должна быть от 2 до 20 символов")
/*    String name;
 //   @Pattern(regexp = "[0-9]{10}", message = "Телефонный номер должен начинаться с +7, затем - 10 цифр")
    String phone;
 //   @Length(max = 500, message = "Описание должно быть длинной не более 500 символов")
    String description;
//    @NotBlank(message = "Необходимо выбрать роль пользователя: админ/покупатель/продавец")*/
    Role role;
 //   @Builder.Default
    Status status = Status.ACTIVE;
}
