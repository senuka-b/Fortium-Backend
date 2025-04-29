package edu.icet.senuka.controller;

import edu.icet.senuka.dto.LoginUser;
import edu.icet.senuka.dto.RegisterUser;
import edu.icet.senuka.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser user) {
        try {
            return ResponseEntity.ok(authService.login(user));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("Invalid login credentials!");
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterUser user) {
        return ResponseEntity.ok(authService.signup(user));
    }

}
