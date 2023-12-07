package ru.acceleration.store.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.security.dto.AuthRequest;
import ru.acceleration.store.security.dto.AuthResponse;
import ru.acceleration.store.security.dto.UserInfoRequestDto;
import ru.acceleration.store.security.dto.UserInfoResponseDto;
import ru.acceleration.store.security.mapper.UserInfoMapper;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.JwtService;
import ru.acceleration.store.security.service.UserInfoService;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserInfoController {

    private final UserInfoService userInfoService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserInfoMapper userInfoMapper;

    @Operation(summary = "Добавление пользователя", description = "Доступ для всех")
    @PostMapping("/registration")
    public UserInfoResponseDto addNewUser(@RequestBody @Valid UserInfoRequestDto userInfoRequestDto) {
        UserInfo userInfo = userInfoMapper.userRequestDtoToUserInfo(userInfoRequestDto);
        userInfo.setRoles("ROLE_USER");
        userInfoService.addUser(userInfo);
        return userInfoMapper.userInfoToUserResponseDto(userInfo);
    }

    @Operation(summary = "Вход пользователя", description = "Доступ для всех")
    @PostMapping("/login")
    public AuthResponse authenticateAndGetToken(@RequestBody @Valid AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserInfo userInfo = userInfoService.getUserInfo(authRequest.getEmail());
            return AuthResponse.builder()
                    .token(jwtService.generateToken(authRequest.getEmail()))
                    .role(userInfo.getRoles())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @Operation(summary = "Изменение пароля", description = "Доступ для всех")
    @PatchMapping("/change")
    public UserInfoResponseDto changePassword(@RequestBody @Valid UserInfoRequestDto userInfoRequestDto) {
        UserInfo userInfo = userInfoMapper.userRequestDtoToUserInfo(userInfoRequestDto);
        userInfoService.changePassword(userInfo);
        return userInfoMapper.userInfoToUserResponseDto(userInfo);
    }
}
