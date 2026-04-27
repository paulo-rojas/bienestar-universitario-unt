package pe.edu.unitru.bienestar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unitru.bienestar.user.domain.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
}
