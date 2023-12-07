package ru.acceleration.store.securiry.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.securiry.dto.AuthRequest;
import ru.acceleration.store.securiry.dto.AuthResponse;
import ru.acceleration.store.securiry.dto.UserInfoRequestDto;
import ru.acceleration.store.securiry.dto.UserInfoResponseDto;
import ru.acceleration.store.securiry.mapper.UserInfoMapper;
import ru.acceleration.store.securiry.model.UserInfo;
import ru.acceleration.store.securiry.service.JwtService;
import ru.acceleration.store.securiry.service.UserInfoService;

import java.security.Principal;

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
    public UserInfoResponseDto addNewUser(@RequestBody UserInfoRequestDto userInfoRequestDto) {
        UserInfo userInfo = userInfoMapper.userRequestDtoToUserInfo(userInfoRequestDto);
        userInfoService.addUser(userInfo);
        return userInfoMapper.userInfoToUserResponseDto(userInfo);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile(Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        String ssss = "Welcome to User  " + userInfo.toString();
        return ssss;
    }

    @Operation(summary = "Вход пользователя", description = "Доступ для всех")
    @PostMapping("/login")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
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
}
