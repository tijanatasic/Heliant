package heliant.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "formular")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class FormularEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @OneToMany(mappedBy = "formular", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<FormularPopunjenEntity> popunjeniFormulari;

    @OneToMany(mappedBy = "formular", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<PoljeEntity> polja;
}
