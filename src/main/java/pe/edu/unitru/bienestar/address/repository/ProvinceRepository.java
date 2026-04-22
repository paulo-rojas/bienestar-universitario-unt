package pe.edu.unitru.bienestar.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.unitru.bienestar.address.domain.ProvinceEntity;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long> {

    public List<ProvinceEntity> getAllByDepartmentId(Long departmentId);
}
