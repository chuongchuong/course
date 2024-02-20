package courseonline4399.online.repository;

import courseonline4399.online.model.UserCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer> {
    Page<UserCourse> findByOwner_Id(int userId ,Pageable pageable);
    List<UserCourse> findByOwner_Id(int userId );
    @Query("Select c From UserCourse c where c.course.id =?1 and c.owner.id =?2")
    UserCourse findByCourseId(int courseid , int userid);
    @Query("SELECT uc FROM UserCourse uc WHERE uc.owner.id = ?1 AND LOWER(uc.course.coursename) LIKE LOWER(CONCAT('%', ?2, '%'))")
    Page<UserCourse> findByOwnerIdAndUserCoursenameContainingIgnoreCase(
            int userId,
            String coursename
            ,Pageable pageable
    );

    @Query("SELECT uc FROM UserCourse uc " +
            "WHERE uc.owner.id = ?1 " +
            "AND LOWER(uc.course.coursename) LIKE LOWER(CONCAT('%', ?2, '%')) " +
            "AND uc.course.category.id = ?3")
    Page<UserCourse> findByOwnerIdAndUserCoursenameContainingIgnoreCaseAndCategory_Id(
            int userId,
            String coursename,int categoryId
            ,Pageable pageable
    );
}
