package courseonline4399.online.repository;

import courseonline4399.online.model.Cartsession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartsessionRepository extends JpaRepository<Cartsession, Integer> {

}
