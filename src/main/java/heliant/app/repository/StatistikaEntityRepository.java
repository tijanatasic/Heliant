package heliant.app.repository;

import heliant.app.entity.StatistikaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistikaEntityRepository extends JpaRepository<StatistikaEntity, Integer> {

}
