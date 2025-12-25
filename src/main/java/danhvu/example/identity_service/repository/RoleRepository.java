package danhvu.example.identity_service.repository;

import danhvu.example.identity_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {
    Set<Role> findByNameIn(Set<String> names);
}
