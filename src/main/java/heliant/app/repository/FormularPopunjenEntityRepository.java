package heliant.app.repository;

import heliant.app.entity.FormularPopunjenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface FormularPopunjenEntityRepository extends JpaRepository<FormularPopunjenEntity, Integer> {

    Integer countByVremeKreiranjaBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
}
