package pe.edu.unitru.bienestar.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unitru.bienestar.patient.domain.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

}
