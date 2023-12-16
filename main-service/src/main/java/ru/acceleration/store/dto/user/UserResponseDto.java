package ru.acceleration.store.dto.user;
import lombok.*;
import ru.acceleration.store.dto.address.AddressResponseDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.security.dto.UserInfoResponseDto;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponseDto {

    private Long id;

    private Boolean enabled;

    private String firstName;

    private String lastName;

    private String phone;

    private Set<ModelShortDto> modelShortDtos;

    private UserInfoResponseDto userInfoResponseDto;

    private List<AddressResponseDto> addressResponseDtos;
}