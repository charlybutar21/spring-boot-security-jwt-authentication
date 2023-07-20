package com.charly.perpetuostudio.controller.user;

import com.charly.perpetuostudio.dto.request.user.LoginRequest;
import com.charly.perpetuostudio.dto.response.user.LoginResponse;
import com.charly.perpetuostudio.service.user.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = loginService.process(loginRequest);

        return ResponseEntity.ok(response);
    }
}
