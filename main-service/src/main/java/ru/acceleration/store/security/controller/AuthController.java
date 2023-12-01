package ru.acceleration.store.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.acceleration.store.exceptions.EntityNotFoundException;
//import ru.acceleration.store.security.config.JwtTokenProvider;
import ru.acceleration.store.security.dto.JwtRequest;
import ru.acceleration.store.security.dto.New;
import ru.acceleration.store.security.dto.UserDto;
import ru.acceleration.store.security.message.ExceptionMessage;
import ru.acceleration.store.security.message.LogMessage;
import ru.acceleration.store.security.model.Role;
import ru.acceleration.store.security.model.User;
import ru.acceleration.store.security.repository.UserRepository;
import ru.acceleration.store.security.service.AuthService;
import ru.acceleration.store.security.service.ChangeService;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ChangeService changeService;
    private final AuthenticationManager authenticationManager;
  //  private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;


/*    @PostMapping("/autoriz")
    public ResponseEntity<Object> authorization(@RequestBody @Valid JwtRequest request) {
        log.info(LogMessage.TRY_AUTHORIZATION.label);

        try {
            UsernamePasswordAuthenticationToken authReq
                    = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication auth = authenticationManager.authenticate(authReq);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);


       //     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String pas=passwordEncoder.encode(request.getPassword());
         //   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), passwordEncoder.encode(request.getPassword())));


            Map<Object, Object> response = new HashMap<>();
            User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.ENTITY_NOT_FOUND_EXCEPTION.label));
            String token = jwtTokenProvider.generateToken(request.getEmail(), user.getRole().name());
            response.put("email", user.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(ExceptionMessage.INVALID_AUTHENTICATION.label, HttpStatus.FORBIDDEN);
        }
    }*/
/*
    @PostMapping("/auth/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        log.info(LogMessage.TRY_LOGOUT.label);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

   @PostMapping("/regist")
    //@RequestMapping("/regist")
    public ResponseEntity<Object> createNewUser(@RequestBody @Valid UserDto userDto) throws ValidationException {
        log.info(LogMessage.TRY_REGISTRATION.label);
        userDto.setConfirmPassword(userDto.getPassword());
        userDto.setRole(Role.BUYER);
        return authService.createNewUser(userDto);
    }

    @PostMapping("/change/pass")
    public ResponseEntity<Object> changePassword(@RequestBody @Validated(New.class) JwtRequest request) {
        log.info(LogMessage.TRY_CHANGE_PASSWORD.label);
        return changeService.changePass(request);
    }*/
}
