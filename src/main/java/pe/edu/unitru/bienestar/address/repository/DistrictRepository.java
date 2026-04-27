package pe.edu.unitru.bienestar.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unitru.bienestar.address.domain.DistrictEntity;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {

    public List<DistrictEntity> getAllByProvinceId(Long provinceId);
}
