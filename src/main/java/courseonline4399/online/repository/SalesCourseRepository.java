package courseonline4399.online.repository;

import courseonline4399.online.constants.CourseSalesDTO;
import courseonline4399.online.model.SalesCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SalesCourseRepository extends JpaRepository<SalesCourse, Integer> {
//    List<SalesCourse> findByStatusSaleFalse();

    // tìm sale course
    @Query("SELECT s FROM SalesCourse s WHERE s.id IN ( " +
            "SELECT MIN(sc.id) FROM SalesCourse sc " +
            "WHERE sc.endsaledate > ?1 AND sc.statussale = false " +
            "GROUP BY sc.course.id)")
    Page<SalesCourse> findAllByEndSaleDateAfterCurrentDateAndStatusSaleIsFalse(Date currentdate,Pageable pageable);

    // tìm theo id
    @Query("SELECT s FROM SalesCourse s WHERE s.course.id = ?1 AND s.endsaledate > ?2 AND s.statussale = false GROUP BY s.course.id")
    List<SalesCourse> findAllByCourseIdAndEndSaleDateAfterCurrentDateAndStatusSaleIsFalse(int courseId, Date currentDate);

    // lấy sale cũ  nhất theo courseid
    @Query("SELECT s FROM SalesCourse s WHERE s.id IN (" +
            "SELECT MIN(sc.id) FROM SalesCourse sc WHERE sc.course.id = ?1 " +
            "AND sc.endsaledate > ?2 AND sc.statussale = false GROUP BY sc.course.id)")
    Page<SalesCourse> findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(int courseId, Date currentDate, Pageable pageable);

    // lấy sale cũ nhất
    @Query("SELECT s FROM SalesCourse s WHERE s.id IN (" +
            "SELECT MIN(sc.id) FROM SalesCourse sc " +
            "WHERE sc.endsaledate > ?1 AND sc.statussale = false GROUP BY sc.course.id)")
    List<SalesCourse> findNewestSalesByEndSaleDateAfterCurrentDateAndStatusSaleIsFalse(Date currentDate);


//Lấy sale dựa trên userId
    @Query("SELECT NEW courseonline4399.online.constants.CourseSalesDTO(c.id, c.coursename, c.price, c.created_by.username, c.status, sc.id, sc.salepercent, sc.endsaledate, sc.statussale) " +
            "FROM Course c " +
            "LEFT JOIN SalesCourse sc ON c.id = sc.course.id " +
            "WHERE c.created_by.id = ?1")
    List<CourseSalesDTO> getCoursesAndSalesByUserId(Integer userId);

}
