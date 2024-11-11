package heliant.app.controller;

import heliant.app.dto.request.LoginRequestDto;
import heliant.app.dto.response.LoginResponseDto;
import heliant.app.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Login", description = "Controller in charge for authentication")
public class LoginController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Login with credentials")
    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        log.info("Received login request for user: {}", loginRequestDto.getUsername());
        LoginResponseDto responseDto = authenticationService.authenticateUser(loginRequestDto);
        log.info("Returning successful login response: {}", responseDto);
        return ResponseEntity.ok(responseDto);
    }
}
