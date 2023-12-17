package ru.acceleration.store.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.acceleration.store.security.config.UserInfoDetails;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = userInfoRepository.findByEmail(email);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }

    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return userInfo;
    }

    public UserInfo getUserInfo(String email) {
        UserInfo userDetail = userInfoRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
        return userDetail;
    }

    public Long getUserId(String email) {
        UserInfo userDetail = userInfoRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
        return userDetail.getId();
    }

    public UserInfo getUserInfobyId(Long id) {
        UserInfo userDetail = userInfoRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found id " + id));
        return userDetail;
    }

    public UserInfo changePassword(UserInfo userInfo) {
        UserInfo userDetail = userInfoRepository.findByEmail(userInfo.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found " + userInfo.getEmail()));
        userDetail.setPassword(encoder.encode(userInfo.getPassword()));
        return userInfoRepository.save(userDetail);
    }


}