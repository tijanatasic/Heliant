package heliant.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "korisnik")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class KorisnikEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "lozinka", nullable = false)
    private String lozinka;
}
