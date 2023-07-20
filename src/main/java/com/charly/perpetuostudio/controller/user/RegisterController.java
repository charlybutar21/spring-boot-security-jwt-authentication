package com.charly.perpetuostudio.controller.user;

import com.charly.perpetuostudio.dto.request.user.RegisterRequest;
import com.charly.perpetuostudio.dto.response.BasicResponse;
import com.charly.perpetuostudio.exception.InvalidRequestException;
import com.charly.perpetuostudio.service.user.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/api/auth/register")
    public ResponseEntity<BasicResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {

        try {
            registerService.process(registerRequest);
            return ResponseEntity.ok(BasicResponse.builder()
                    .message("Success")
                    .build());

        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().body(BasicResponse.builder()
                    .message(e.getMessage())
                    .build());
        }

    }

}
