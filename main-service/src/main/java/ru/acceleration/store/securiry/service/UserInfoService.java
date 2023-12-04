package ru.acceleration.store.securiry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.UserRepository;
import ru.acceleration.store.securiry.config.UserInfoDetails;
import ru.acceleration.store.securiry.model.UserInfo;
import ru.acceleration.store.securiry.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByEmail(email);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }

    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        User user = new User();
        user.setUserInfo(userInfo);
        repository.save(userInfo);
        userRepository.save(user);
        return userInfo;
    }

    public UserInfo getUserInfo(String email) {
        UserInfo userDetail = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
        return userDetail;
    }

    public UserInfo getUserInfobyId(Long id) {
        UserInfo userDetail = repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found id " + id));
        return userDetail;
    }
}