package heliant.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "vreme_kreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "vreme_poslednje_izmene", nullable = false)
    private LocalDateTime vremePoslednjeIzmene;

}
