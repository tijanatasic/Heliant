package heliant.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "polje_popunjeno")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class PoljePopunjenoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_formular_popunjen", nullable = false)
    private FormularPopunjenEntity formularPopunjen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_polje", nullable = false)
    private PoljeEntity polje;

    @Column(name = "vrednost_tekst", nullable = false)
    private String vrednostTekst;

    @Column(name = "vrednost_broj", nullable = false)
    private Double vrednostBroj;

}
