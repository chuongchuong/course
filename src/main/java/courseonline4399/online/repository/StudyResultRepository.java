package courseonline4399.online.repository;



import courseonline4399.online.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface StudyResultRepository extends JpaRepository<StudyResult, Integer> {

    @Query("SELECT o FROM StudyResult o WHERE o.user.id =?1 and o.detail.id=?2")
    StudyResult findByUserAndDetail(Integer userid , Integer detailid);


    @Query("SELECT sr FROM StudyResult sr " +
            "JOIN sr.detail cd " +
            "JOIN cd.course c " +
            "WHERE c.id = ?1 and sr.status=true and sr.user.id=?2")
    List<StudyResult> findCompletedQuizByCourseId(int courseId , int userid);

}
