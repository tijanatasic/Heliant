package heliant.app.entity;

import heliant.app.enums.TipPoljaEnum;
import heliant.app.enums.TipPoljaEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "polje")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class PoljeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_formular", nullable = false)
    private FormularEntity formular;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "prikazni_redosled", nullable = false)
    private Integer prikazniRedosled;

    @Column(name = "tip", nullable = false)
    @Convert(converter = TipPoljaEnumConverter.class)
    @Enumerated(EnumType.STRING)
    private TipPoljaEnum tip;
}
