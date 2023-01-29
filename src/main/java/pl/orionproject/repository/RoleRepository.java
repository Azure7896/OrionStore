package pl.orionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.orionproject.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
