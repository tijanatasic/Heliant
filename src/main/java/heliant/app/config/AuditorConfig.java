package heliant.app.config;

import heliant.app.entity.KorisnikEntity;
import heliant.app.service.impl.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorConfig {

    @Bean
    public AuditorAware<KorisnikEntity> auditorAware() {
        return new AuditorAwareImpl();
    }

}
