package heliant.app.service.impl;

import heliant.app.entity.KorisnikEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<KorisnikEntity> {

    @Override
    public Optional<KorisnikEntity> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return Optional.empty();
        }
        return Optional.of((KorisnikEntity) authentication.getPrincipal());
    }
}
