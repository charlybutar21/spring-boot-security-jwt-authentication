package com.charly.perpetuostudio.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String token;
    private String type;
    private String username;
    private String email;
    private String name;
    private List<String> roles;

}
