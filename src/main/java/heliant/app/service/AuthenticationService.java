package heliant.app.service;

import heliant.app.dto.request.LoginRequestDto;
import heliant.app.dto.response.LoginResponseDto;

public interface AuthenticationService {
    LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto);
}
