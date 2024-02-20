package courseonline4399.online.repository;

import courseonline4399.online.model.Cartsession;
import courseonline4399.online.model.Order;
import courseonline4399.online.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {


    @Query("SELECT o FROM Quiz o WHERE o.coursedetail.id =?1 ")
    List<Quiz> findByCourseDetailId(Integer userid);
}
