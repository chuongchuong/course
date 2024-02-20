package courseonline4399.online.repository;

import courseonline4399.online.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
 @Query("SELECT c FROM Course c WHERE c.created_by.id = ?1")
 List<Course> findBycreated_by_Id(int userId);
 Page<Course> findAll(Pageable pageable);
 Page<Course> findByCoursenameContainingIgnoreCase(String coursename, Pageable pageable);
 Page<Course> findByCoursenameContainingIgnoreCaseAndCategory_Id(String coursename, Integer categoryId, Pageable pageable);

 Page<Course> findAllByStatusIsFalse(Pageable pageable);
 List<Course> findByStatusTrue();

 Page<Course> findByCoursenameContainingIgnoreCaseAndCategory_IdAndStatusIsFalse(
         String coursename, Integer categoryId, Pageable pageable
 );

 @Query("SELECT c FROM Course c WHERE c.coursename LIKE %?1% AND c.status = false")
 Page<Course> findByCoursenameContainingIgnoreCaseAndStatusIsFalse(
         String coursename, Pageable pageable
 );

 //Phân giá
 // Find all courses with status false and order by price ascending with pagination
 Page<Course> findByStatusOrderByPriceAsc(boolean status, Pageable pageable);

 // Find all courses with status false and order by price descending with pagination
 Page<Course> findByStatusOrderByPriceDesc(boolean status, Pageable pageable);

 // Find courses with status false and price between min and max with pagination
 Page<Course> findByStatusAndPriceBetween(boolean status, Double min, Double max, Pageable pageable);

 // Find top 3 courses with status false ordered by created date descending
 @Query("SELECT c FROM Course c WHERE c.status = false ORDER BY c.createddate DESC")
 List<Course> findTop3();
}
