package heliant.app.repository;

import heliant.app.entity.KorisnikEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikEntityRepository extends JpaRepository<KorisnikEntity, Integer> {

    Optional<KorisnikEntity> findByKorisnickoIme(String username);
}
