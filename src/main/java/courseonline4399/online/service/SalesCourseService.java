package courseonline4399.online.service;

import courseonline4399.online.constants.CourseSalesDTO;
import courseonline4399.online.model.Course;
import courseonline4399.online.model.SalesCourse;
import courseonline4399.online.repository.CourseRepository;
import courseonline4399.online.repository.SalesCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalesCourseService implements CRUD<SalesCourse,Integer>{
    @Autowired
    SalesCourseRepository repository;
@Autowired
CourseService courseService;

    @Override
    public SalesCourse create(SalesCourse salesCourse) {
        return repository.save(salesCourse);
    }
    public SalesCourse createSales(Integer id,Float salepercent,Date date) {
        SalesCourse salesCourse = new SalesCourse();
        salesCourse.setCourse(courseService.findById(id));
        salesCourse.setSalepercent(salepercent);
        salesCourse.setEndsaledate(date);
        salesCourse.setStatussale(false);
        return repository.save(salesCourse);
    }
    public SalesCourse updateCourse(Integer id,Float salepercent,Date date) {
    SalesCourse salesCourse = repository.findById(id).orElse(null);
    if(salesCourse!=null){
        salesCourse.setSalepercent(salepercent);
        salesCourse.setStatussale(false);
        salesCourse.setEndsaledate(date);
        return  repository.save(salesCourse);
    }

        return null;
    }
    public SalesCourse ChangeStatus(Integer id,Boolean status) {
        SalesCourse salesCourse = repository.findById(id).orElse(null);
        if(salesCourse!=null){

            salesCourse.setStatussale(status);

            return  repository.save(salesCourse);
        }

        return null;
    }

    @Override
    public SalesCourse update(SalesCourse salesCourse) {return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<SalesCourse> findAll() {
        return null;
    }

    @Override
    public SalesCourse findById(Integer integer) {
        return repository.findById(integer).orElse(null);
    }

    public List<SalesCourse> getSalesCoursesWithEndSaleDateAfterCurrentDateAndStatusSaleIsFalse() {
        // Get the current date
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());

        // Call the repository method to find SalesCourses
        return repository.findNewestSalesByEndSaleDateAfterCurrentDateAndStatusSaleIsFalse(currentDate);
    }

    public Page<SalesCourse> getSalesCoursesWithEndSaleDateAfterCurrentDateAndStatusSaleIsFalse(Pageable pageable) {
        // Get the current date
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());

        // Call the repository method to find SalesCourses
        return repository.findAllByEndSaleDateAfterCurrentDateAndStatusSaleIsFalse(currentDate,pageable);
    }
    public Page<SalesCourse> findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(int courseId, Pageable pageable) {
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        return repository.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(courseId, currentDate,pageable);
    }

    public List<CourseSalesDTO> getCoursesAndSalesByUserId(Integer userId) {
        List<CourseSalesDTO> coursesWithSales = repository.getCoursesAndSalesByUserId(userId);
        return coursesWithSales;
    }


}
