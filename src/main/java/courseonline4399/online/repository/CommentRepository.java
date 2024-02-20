package courseonline4399.online.repository;

import courseonline4399.online.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommentRepository extends JpaRepository<Comment, Integer> {
 @Query("SELECT c FROM Comment c WHERE c.course.id = ?1 AND c.status = ?2 ORDER BY c.createddate ASC")
 List<Comment> findAllByCourseIdAndStatusOrderByCreatedDateAsc( int courseId,  boolean status);

}
