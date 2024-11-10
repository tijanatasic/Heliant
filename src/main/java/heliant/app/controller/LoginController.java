package heliant.app.controller;

import heliant.app.dto.LoginRequestDto;
import heliant.app.dto.LoginResponseDto;
import heliant.app.service.AuthenticationService;
import heliant.app.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        log.info("Received login request for user: {}", loginRequestDto.getUsername());
        LoginResponseDto responseDto = authenticationService.authenticateUser(loginRequestDto);
        log.info("Returning successful login response: {}", responseDto);
        return ResponseEntity.ok(responseDto);
    }
}
