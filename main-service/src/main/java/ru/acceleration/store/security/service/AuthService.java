package ru.acceleration.store.security.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.acceleration.store.security.dto.UserDto;
import ru.acceleration.store.security.exception.WrongRegException;
import ru.acceleration.store.security.mapper.UserMapper;
import ru.acceleration.store.security.message.ExceptionMessage;
import ru.acceleration.store.security.model.Status;
import ru.acceleration.store.security.model.User;
import ru.acceleration.store.security.repository.UserRepository;

import java.util.Optional;

import static ru.acceleration.store.security.model.Role.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
   // private final AdminService adminService;
  //  private final SellerService sellerService;
  //  private final BuyerService buyerService;
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Object> createNewUser(UserDto userDto) throws ValidationException {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new WrongRegException(ExceptionMessage.CONFIRMED_PASSWORD_EXCEPTION.label);
        }

        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(Status.ACTIVE);

        switch (userDto.getRole()) {
            case ADMIN:
       //         log.info(LogMessage.TRY_ADD_ADMIN.label);

             //   adminService.addAdmin(userDto);
                break;
            case SELLER:
       //         log.info(LogMessage.TRY_ADD_SELLER.label);

           //     sellerService.addSeller(userDto);
                break;
            case BUYER:
         //       log.debug(LogMessage.TRY_ADD_BUYER.label);

           //     buyerService.addBuyer(userDto);
                break;
        }

        return ResponseEntity.of(Optional.of(UserMapper.INSTANCE.userToUserResponseDto(repository.save(user))));
    }
}