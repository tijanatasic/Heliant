package heliant.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formular_popunjen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class FormularPopunjenEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formular", nullable = false)
    private FormularEntity formular;

    @OneToMany(mappedBy = "formularPopunjen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<PoljePopunjenoEntity> popunjenaPolja = new ArrayList<>();

}
