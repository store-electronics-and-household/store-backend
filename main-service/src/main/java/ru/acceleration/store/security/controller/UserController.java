package ru.acceleration.store.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.security.dto.AuthRequest;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.JwtService;
import ru.acceleration.store.security.service.UserInfoService;

import java.security.Principal;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

  //  @PostMapping("/addNewUser")
    @PostMapping("/regist")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

   // @GetMapping("/user/userProfile")
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile(Principal principal) {
        UserInfo userInfo=service.getUserInfo(principal.getName());
        String ssss="Welcome to User  "+userInfo.toString();
        return ssss;
    }

/*    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }*/

    @PostMapping("/autoriz")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
