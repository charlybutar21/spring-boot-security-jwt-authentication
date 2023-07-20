package com.charly.perpetuostudio.service.user;

import com.charly.perpetuostudio.dto.request.user.RegisterRequest;
import com.charly.perpetuostudio.exception.InvalidRequestException;

public interface RegisterService {

    void process(RegisterRequest request) throws InvalidRequestException;
}
