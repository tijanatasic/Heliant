package heliant.app.service.impl;

import heliant.app.dto.request.LoginRequestDto;
import heliant.app.dto.response.LoginResponseDto;
import heliant.app.entity.KorisnikEntity;
import heliant.app.service.AuthenticationService;
import heliant.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) {
        Authentication authenticatedUser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        log.info("User is successfully authenticated: {}", authenticatedUser);
        KorisnikEntity korisnikEntity = (KorisnikEntity) authenticatedUser.getPrincipal();
        Map<String, Object> claims = generateClaims(korisnikEntity);
        log.info("Generated custom claims: {}", claims);
        String token = jwtService.generateToken(claims, korisnikEntity);
        return new LoginResponseDto(token);
    }

    private Map<String, Object> generateClaims(KorisnikEntity korisnikEntity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", korisnikEntity.getId());
        claims.put("username", korisnikEntity.getKorisnickoIme());
        claims.put("roles", korisnikEntity.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return claims;
    }
}
