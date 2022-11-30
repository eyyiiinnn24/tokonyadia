package com.mandiri.tokonyadia.controller;
import com.mandiri.tokonyadia.dto.LoginDTO;
import com.mandiri.tokonyadia.entity.AppUser;
import com.mandiri.tokonyadia.service.AuthService;
import com.mandiri.tokonyadia.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> response(@RequestBody AppUser appUser) {
        AppUser register = authService.register(appUser);

        Response<?> response = new Response<>(
                "Successfully created new user", register
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/login")
    public  ResponseEntity<?> login (@RequestBody AppUser appUser){
        LoginDTO login= authService.login(appUser);
        Response<?> response= new Response<>("Success Login!",login);
        return  ResponseEntity.ok(response);
    }
}
