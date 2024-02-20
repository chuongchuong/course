package courseonline4399.online.repository;

import courseonline4399.online.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepository extends JpaRepository<Role, String> {
 }
