package pe.edu.unitru.bienestar.shared.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unitru.bienestar.shared.domain.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    boolean existsByDni(String dni);

    PersonEntity findByDni(String dni);
}
