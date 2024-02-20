package courseonline4399.online.controller;

import courseonline4399.online.model.Course;
import courseonline4399.online.model.CourseDetail;
import courseonline4399.online.model.CourseVideo;
import courseonline4399.online.model.SalesCourse;
import courseonline4399.online.repository.CourseRepository;
import courseonline4399.online.service.CourseDetailService;
import courseonline4399.online.service.CourseService;
import courseonline4399.online.service.SalesCourseService;
//import courseonline4399.online.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class Course_detailController {
	@Autowired
	CourseService service;
	@Autowired
	CourseDetailService videoService;
	@Autowired
	SalesCourseService salesCourseService;

	@GetMapping("/detail_course/{id}")
	public String detail_product(Model model , @PathVariable("id") Integer id) {
		Course course= service.findById(id);
		// Fetch sales information for each course
		List<Float> salePercents = new ArrayList<>();
		List<Date> saleEndDates = new ArrayList<>();
		Pageable unlimitedPage = Pageable.unpaged();//unlimited retrieve
		// Replace pageSize with the desired size of the sales information page
		Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(id,unlimitedPage);
		Float changePrices =null;
		// Calculate the change price for each course
		if(salesCoursePage !=null){
			// Get the content of the first page of sales information
			List<SalesCourse> salesCourses = salesCoursePage.getContent();
			if (!salesCourses.isEmpty()) {
				float salePercent = salesCourses.get(0).getSalepercent(); // Assuming you need the first sale percent
				Date saleEndDate = salesCourses.get(0).getEndsaledate();
				salePercents.add(salePercent);
				saleEndDates.add(saleEndDate);
				Float percent = salePercents.get(0);
				Float originalPrice = course.getPrice();
				changePrices = originalPrice * (1 - percent / 100); // Calculate the change price
			} else {
				salePercents.add(null); // or add a default value as needed
				saleEndDates.add(null);
			}
		}





		if (course != null) {
			List<CourseDetail> videos = videoService.findVideosByCourseId(course.getId());
			model.addAttribute("salePercents", salePercents);
			model.addAttribute("changePrices", changePrices);
			model.addAttribute("saleEndDates", saleEndDates);
			model.addAttribute("course", course);
			model.addAttribute("videos", videos);
		}
		return "user/course_detail.html";
	}
}
