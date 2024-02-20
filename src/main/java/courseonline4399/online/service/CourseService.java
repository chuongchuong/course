package courseonline4399.online.service;

import courseonline4399.online.model.Category;
import courseonline4399.online.model.Course;
import courseonline4399.online.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService implements CRUD<Course,Integer> {
    @Autowired
    CourseRepository repository;
    @Autowired
    CategoryService caterepo;
    @Autowired
    AccountService accountService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    FileManagerService fileManagerService;

    @Override
    public Course create(Course course) {
        return  repository.save(course);
    }
    public Course create(String name , String price , String desc , String cate , MultipartFile file , Model model) {
        Course course = new Course();
        course.setCoursename(name);
        course.setPrice(Float.valueOf(price));
        course.setDescriptions(desc);
        course.setCreated_by(accountService.getCurrentUserInfo());
        course.setCreateddate(new Date());
        course.setStatus(Boolean.FALSE);
        course.setUpdatedate(new Date());
        Category category = categoryService.findByname(cate);
        if(category != null){
            course.setCategory(category);
            String imageName = fileManagerService.save("files/" , file);
            course.setImage(imageName);
            if(validate(course,model) )
                return create(course);
        }
        return  null;

    }
    public Boolean validate( Course course , Model model){
        boolean check = true;
        if(course.getCoursename().isEmpty())
        {
            check = false;
            model.addAttribute("coursename" ,  "Không được để trống tên;");
        }

        if(course.getImage().isEmpty())
        {check = false;
            model.addAttribute("image" ,  "Không được để trống Hinh;");}

        if(course.getCategory() == null)
        {
            check = false;
            model.addAttribute("category" ,  "Không được để trống Thể ;");
        }


        if(course.getPrice() < 1 || course.getPrice() > 5000000) {
            check = false;
            model.addAttribute("price", "Giá tiền[1.000,5.000.000]");
        }

        return check;
    }

    @Override
    public Course update(Course course) {
        return create(course);
    }
    public Course update(Integer id , String name , String price , String desc , String cate , MultipartFile file , Model model) {
        Course course = repository.findById(id).get();

        if (course != null) {
            course.setCoursename(name);
            course.setPrice(Float.valueOf(price));
            course.setDescriptions(desc);
            course.setUpdatedate(new Date());
            Category category = categoryService.findByname(cate);
            if(category != null){
                course.setCategory(category);
            }
            if(file != null){
                String imageName = fileManagerService.save("files/" , file);
                course.setImage(imageName);
            }
        }
        return update(course);
    }

    @Override
    public void delete(Integer integer) {
        Course course = findById(integer);
        if(course != null){
            course.setStatus(true);
            repository.save(course);
        }

    }


    @Override
    public List<Course> findAll() {
//        return repository.findByStatusTrue();
        return repository.findAll();
    }
    public List<Course> findByUserId(Integer userId){
        return repository.findBycreated_by_Id(userId);
    }

    public  Page<Course> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Course> searchCoursesByName(String coursename, Pageable pageable) {
        return repository.findByCoursenameContainingIgnoreCase(coursename, pageable);
    }
    public Page<Course> searchCoursesByNameAndCategory(String coursename, Integer categoryId, Pageable pageable) {
        return repository.findByCoursenameContainingIgnoreCaseAndCategory_Id(coursename, categoryId, pageable);
    }
    @Override
    public Course findById(Integer integer) {
        return repository.findById(integer).orElse(null);
    }




//tìm status = false
    public Page<Course> getAllInactiveCourses(Pageable pageable) {
        return repository.findAllByStatusIsFalse(pageable);
    }

    public Page<Course> findCoursesByCoursenameAndCategoryAndStatus(String coursename, Integer categoryId, Pageable pageable) {
        return repository.findByCoursenameContainingIgnoreCaseAndCategory_IdAndStatusIsFalse(coursename, categoryId, pageable);
    }
    public Page<Course> findCoursesByCoursenameAndStatus(String coursename, Pageable pageable) {
        return repository.findByCoursenameContainingIgnoreCaseAndStatusIsFalse(coursename, pageable);
    }

    public  Page<Course> getAllCourseSortedByPriceAsc(Pageable pageable) {
        return repository.findByStatusOrderByPriceAsc(false,pageable);
    }
    public  Page<Course> getAllCourseSortedByPriceDesc(Pageable pageable) {
        return repository.findByStatusOrderByPriceDesc(false,pageable);
    }
    public  Page<Course> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable) {
        return  repository.findByStatusAndPriceBetween( false, minPrice, maxPrice, pageable);
    }
    public  List<Course> findTop3(){
        return repository.findTop3();
    }

}
