package courseonline4399.online.repository;

import courseonline4399.online.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByusername(String username);

    @Query("SELECT u FROM User u JOIN Userole ur ON u.id = ur.user.id JOIN Role r ON ur.role.id = r.id WHERE r.name = 'TEACHER'")
    List<User> findTeachers();

    User findByEmail(String email);
    User findByToken(String token);
    @Modifying
    @Query("UPDATE User u SET u.token = :token WHERE u.email = :email")
    @Transactional
    void updateTokenByEmail(String email, String token);
}
