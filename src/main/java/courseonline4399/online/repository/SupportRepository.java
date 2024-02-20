package courseonline4399.online.repository;

import courseonline4399.online.model.Support;
import courseonline4399.online.model.Userole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SupportRepository extends JpaRepository<Support, Integer> {
    @Query("SELECT o FROM Support o WHERE o.email =?1")
    List<Support> findByEmail(String email);
}
