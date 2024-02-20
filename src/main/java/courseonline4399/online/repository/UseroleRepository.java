package courseonline4399.online.repository;


import courseonline4399.online.model.User;
import courseonline4399.online.model.Userole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UseroleRepository extends JpaRepository<Userole, Integer> {

    // You can add custom query methods here if needed
    List<Userole> findAllByUser(User account);

    @Query("SELECT o FROM Userole o WHERE o.user.id =?1")
    Userole findByIdUser(Integer userid);
}
