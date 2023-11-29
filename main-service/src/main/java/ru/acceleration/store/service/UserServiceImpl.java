package ru.acceleration.store.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.UserCreateDto;
import ru.acceleration.store.dto.UserDto;
import ru.acceleration.store.mapper.AuthorityMapper;
import ru.acceleration.store.mapper.UserMapper;
import ru.acceleration.store.model.Authority;
import ru.acceleration.store.model.Role;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.AuthorityRepo;
import ru.acceleration.store.repository.UserRepository;

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
    public UserDto postUser(UserCreateDto userCreateDto) {
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        UserDto userDto = userMapper.toUserDto(userCreateDto);
        User user = userRepository.save(userMapper.toUser(userDto));
        Authority authority = authorityMapper.toAuthority(userCreateDto);
        authority.setRole(Role.ROLE_USER);
        authorityRepo.save(authority);
        return userMapper.toUserDto(user);
    }


//    private void validate(UserCreateDto userCreateDto) {
//        Set<String> categoryNames = new HashSet<>(userRepository.findUserByName());
//        if (categoryNames.contains(newAccessDataDto.getUsername())) {
//            throw new ConflictException("AccessData username " + newAccessDataDto.getUsername() + " already used");
//        }
//    }
}
