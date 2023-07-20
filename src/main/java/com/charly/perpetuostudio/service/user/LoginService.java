package com.charly.perpetuostudio.service.user;

import com.charly.perpetuostudio.dto.request.user.LoginRequest;
import com.charly.perpetuostudio.dto.response.user.LoginResponse;

public interface LoginService {
    LoginResponse process(LoginRequest request);

}
