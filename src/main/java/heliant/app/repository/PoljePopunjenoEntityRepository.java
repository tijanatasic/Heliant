package heliant.app.repository;

import heliant.app.entity.PoljePopunjenoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoljePopunjenoEntityRepository extends JpaRepository<PoljePopunjenoEntity, Integer> {

}
