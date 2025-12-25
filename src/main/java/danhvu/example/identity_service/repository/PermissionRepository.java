package danhvu.example.identity_service.repository;

import danhvu.example.identity_service.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {

}
