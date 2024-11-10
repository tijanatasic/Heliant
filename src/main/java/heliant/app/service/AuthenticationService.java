package heliant.app.service;

import heliant.app.dto.LoginRequestDto;
import heliant.app.dto.LoginResponseDto;

public interface AuthenticationService {
    LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto);
}
