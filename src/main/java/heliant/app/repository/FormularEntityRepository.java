package heliant.app.repository;

import heliant.app.entity.FormularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularEntityRepository extends JpaRepository<FormularEntity, Integer> {

}
