package heliant.app.entity;

import heliant.app.enums.KorisnikRolaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "korisnik")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KorisnikEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "korisnicko_ime", nullable = false)
    private String korisnickoIme;

    @Column(name = "lozinka", nullable = false)
    private String lozinka;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "vreme_kreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "vreme_poslednje_izmene", nullable = false)
    private LocalDateTime vremePoslednjeIzmene;

    @Column(name = "rola")
    @Enumerated(EnumType.STRING)
    private KorisnikRolaEnum rola;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (rola == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(rola.name()));
    }

    @Override
    public String getPassword() {
        return lozinka;
    }

    @Override
    public String getUsername() {
        return korisnickoIme;
    }
}
