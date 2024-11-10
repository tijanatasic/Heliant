package heliant.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "korisnik")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class KorisnikEntity extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "korisnicko_ime", nullable = false)
    private String korisnickoIme;

    @Column(name = "lozinka", nullable = false)
    private String lozinka;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
