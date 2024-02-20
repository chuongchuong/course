package courseonline4399.online.controller;

import courseonline4399.online.model.*;
import courseonline4399.online.repository.UserRepository;
import courseonline4399.online.service.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LibraryController {
    @Autowired
    AccountService accountService;
    @Autowired
    UserCourseService userCourseService;
    @Autowired
    CourseDetailService videoService;
    @Autowired
    CourseService courseService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SupportService supportService;
    @Autowired
    StudyResultService studyResultService;
    @Autowired
    private MailerService mailer;
    int pageSize = 6;

    @GetMapping(value = {"/library"})
    public String Lib(Model model, @RequestParam(name = "page", defaultValue = "0") int page){
        User user = accountService.getCurrentUserInfo();
        Page<UserCourse> userCourses = userCourseService.findUserCoursesByUserId(user.getId(), PageRequest.of(page, pageSize));

        List<UserCourse> courses = userCourses.getContent();


        Map<Integer, Integer> totalResultsMap = new HashMap<>();
        Map<Integer, Integer> totalVideosMap = new HashMap<>();
        for (UserCourse course : courses) {
            List<StudyResult> studyResults1 = studyResultService.findQuiz(course.getCourse().getId(), user.getId());
            List<CourseDetail> courseDetails = videoService.findVideosByCourseId(course.getCourse().getId());

            int totalVideo = courseDetails.size();
            int totalResult = studyResults1.size();

            // Add the totalResult to the map with the course ID as the key
            totalVideosMap.put(course.getCourse().getId(),totalVideo);
            totalResultsMap.put(course.getCourse().getId(), totalResult);
        }
        model.addAttribute("paginationFragment", "library_default");//phân trang
        model.addAttribute("currentUser" , user);

        model.addAttribute("userCourses", userCourses.getContent()); //Hiển thị các khóa học nd sở hữu
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userCourses.getTotalPages());
        //Hiển thị tổng quiz và tổng video
        model.addAttribute("quiz", totalResultsMap);
        model.addAttribute("video", totalVideosMap);
        return "user/user_library";
    }
    //search
    @GetMapping("/library/timkiem")
    public String getUserCoursesByOwnerIdAndCoursename( @RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name="coursename", defaultValue = "") String coursename
            ,@RequestParam(name = "categoryId", defaultValue = "0") Integer categoryId,Model model) {
        User user = accountService.getCurrentUserInfo();
        Page<UserCourse> userCourses;
        int ownerId = user.getId();
        if(coursename!=null && categoryId !=0){
            userCourses = userCourseService.findByOwnerIdAndUserCoursenameAndCategoryId(ownerId, coursename,categoryId, PageRequest.of(page, pageSize));

        }else if (coursename!=null){
            userCourses = userCourseService.findByOwnerIdAndUserCoursenameContainingIgnoreCase(ownerId, coursename, PageRequest.of(page, pageSize));

        }else{
            userCourses = userCourseService.findUserCoursesByUserId(user.getId(), PageRequest.of(page, pageSize));
        }
        Map<Integer, Integer> totalResultsMap = new HashMap<>();
        Map<Integer, Integer> totalVideosMap = new HashMap<>();
        for (UserCourse course : userCourses.getContent()) {
            List<StudyResult> studyResults1 = studyResultService.findQuiz(course.getCourse().getId(), user.getId());
            List<CourseDetail> courseDetails = videoService.findVideosByCourseId(course.getCourse().getId());

            int totalVideo = courseDetails.size();
            int totalResult = studyResults1.size();

            // Add the totalResult to the map with the course ID as the key
            totalVideosMap.put(course.getCourse().getId(),totalVideo);
            totalResultsMap.put(course.getCourse().getId(), totalResult);
        }
        model.addAttribute("paginationFragment", "library_search");//phân trang
        model.addAttribute("userCourses", userCourses.getContent()); //Hiển thị các khóa học nd sở hữu
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userCourses.getTotalPages());
        model.addAttribute("quiz", totalResultsMap);
        model.addAttribute("video", totalVideosMap);
        return "user/user_library";
    }

    @ModelAttribute("cates")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }
    @GetMapping(value = {"/library/video/{id}"})
    public String video(Model model, @PathVariable("id") Integer id,@RequestParam(name = "page", defaultValue = "0") int page){
        Course course= courseService.findById(id);
        // truyen vao detailid

//        model.addAttribute("quiz_id" ,);
        if (course == null) {
            return "user/user_library";
        }


        List<CourseDetail> courseVideos = videoService.findVideosByCourseId(course.getId());
        Pageable pageable = Pageable.unpaged();//unlimited retrieve
        User user = accountService.getCurrentUserInfo();
        Page<UserCourse> userCourses = userCourseService.findUserCoursesByUserId(user.getId(),pageable); //kiem tra khoa hoc user
        boolean courseExistsInUserCourses = userCourses.stream()
                .anyMatch(userCourse -> userCourse.getCourse().equals(course));

        model.addAttribute("course", course);

        if (courseExistsInUserCourses &&courseVideos !=null) {

            model.addAttribute("error",null);

        } else {
            model.addAttribute("error","Bạn chưa sở hữu khóa học");
        }

        return "user/user_video";
    }


    @PostMapping("/library/support/{courseId}/{createdByEmail}")
    public String  Support(@PathVariable("courseId") Integer courseId
            , @PathVariable("createdByEmail") String createdByEmail
            , @RequestParam("noiDung") String noiDung, Model model) throws MessagingException {
        Support support = new Support();
        support.setUser(accountService.getCurrentUserInfo());
        support.setEmail(createdByEmail);
        support.setCourse(courseService.findById(courseId));
        support.setNoiDung(noiDung);
        support.setStatus(0);
        supportService.create(support);

        mailer.send(new MailInfo(createdByEmail, "Hỗ trợ học sinh ",
                "Hỗ trợ học sinh có email : " + accountService.getCurrentUserInfo().getEmail() +" nội dung hỗ trợ : "+noiDung));
        model.addAttribute("successMessage","Thành công! Dữ liệu đã được xử lý. " +
                "Giảng viên sẽ liên hệ với bạn trong thời gian sớm nhất");
        return "redirect:/library/video/" + courseId;
    }

}

