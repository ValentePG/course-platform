//package dev.valente.course_platform.devs.controller;
//
//import dev.valente.course_platform.devs.service.AuthService;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RestController
//public class AuthController {
//
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping("authenticate")
//    public String authenticate(Authentication authentication){
//        return authService.authenticate(authentication);
//    }
//}
