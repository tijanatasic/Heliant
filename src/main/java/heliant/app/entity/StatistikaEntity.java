package heliant.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "statistika")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatistikaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "broj_popunjenih_formulara", nullable = false)
    private Integer brojPopunjenihFormulara;

}
