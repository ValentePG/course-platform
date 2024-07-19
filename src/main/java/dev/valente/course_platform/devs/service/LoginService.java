package dev.valente.course_platform.devs.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    AuthService authService;

    public LoginService(AuthService authService) {
        this.authService = authService;
    }
}
