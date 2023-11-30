package ru.acceleration.store.service.user;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.user.UserRequestDto;
import ru.acceleration.store.dto.user.UserResponseDto;
import ru.acceleration.store.mapper.AuthorityMapper;
import ru.acceleration.store.mapper.UserMapper;

import ru.acceleration.store.model.Authority;
import ru.acceleration.store.model.Role;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.AuthorityRepo;
import ru.acceleration.store.repository.UserRepository;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepo authorityRepo;
    private final UserMapper userMapper;
    private final AuthorityMapper authorityMapper;

    @Override
    public UserResponseDto postUser(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        UserResponseDto userResponseDto = userMapper.toUserDto(userRequestDto);
        User user = userRepository.save(userMapper.toUser(userResponseDto));
        Authority authority = authorityMapper.toAuthority(userRequestDto);
        authority.setRole(Role.ROLE_USER);
        user.setAuthorities(new ArrayList<>());
        user.getAuthorities().add(authority);
        authorityRepo.save(authority);
        return userMapper.toUserDto(user);
    }


//    private void validate(UserRequestDto userCreateDto) {
//        Set<String> categoryNames = new HashSet<>(userRepository.findUserByName());
//        if (categoryNames.contains(newAccessDataDto.getUsername())) {
//            throw new ConflictException("AccessData username " + newAccessDataDto.getUsername() + " already used");
//        }
//    }
}
