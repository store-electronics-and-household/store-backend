package ru.acceleration.store.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.acceleration.store.security.dto.JwtRequest;
import ru.acceleration.store.security.exception.WrongRegException;
import ru.acceleration.store.security.mapper.UserMapper;
import ru.acceleration.store.security.message.ExceptionMessage;
import ru.acceleration.store.security.model.User;
import ru.acceleration.store.security.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeService {
    private final UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<Object> changePass(JwtRequest request) throws WrongRegException {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new WrongRegException(ExceptionMessage.CONFIRMED_PASSWORD_EXCEPTION.label);
        }

        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new WrongRegException(ExceptionMessage.ENTITY_NOT_FOUND_EXCEPTION.label));
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return ResponseEntity.of(Optional.of(UserMapper.INSTANCE.userToUserResponseDto(repository.save(user))));
    }

    public void changeEmail(String oldEmail, String newEmail) {
        User user = repository.findByEmail(oldEmail).orElseThrow(() -> new WrongRegException(ExceptionMessage.ENTITY_NOT_FOUND_EXCEPTION.label));
        user.setEmail(newEmail);
        repository.save(user);
    }
}
