package pe.edu.unitru.bienestar.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unitru.bienestar.address.domain.DistrictEntity;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
}
