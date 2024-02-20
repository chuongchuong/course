package courseonline4399.online.controller;


import courseonline4399.online.model.Course;
import courseonline4399.online.model.SalesCourse;
import courseonline4399.online.service.CourseService;
import courseonline4399.online.service.SalesCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
	@Autowired
	CourseService service;
	@Autowired
	SalesCourseService salesCourseService;

	 @GetMapping(value = {"/home" , "/" , ""})
	    public String Home(Model model){
		 //Khóa học khác
		 List<Course> courses = service.findAll();
		 if (courses.size() > 3) {
			 courses = courses.subList(0, 3); // Limit the list to 3 items
		 }
		 //Khóa học dc sale

		 List<Float> salePercents = new ArrayList<>();
		 List<Date> saleEndDates = new ArrayList<>();

		 List<SalesCourse> salesCourses = salesCourseService.getSalesCoursesWithEndSaleDateAfterCurrentDateAndStatusSaleIsFalse();
		 for (SalesCourse sales : salesCourses) {
			 if (salesCourses != null) {
				 Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(sales.getCourse().getId(), Pageable.unpaged());
				 List<SalesCourse> salesCourse = salesCoursePage.getContent();
				 float salePercent = salesCourse.get(0).getSalepercent();
				 Date saleEndDate = salesCourse.get(0).getEndsaledate();
				 salePercents.add(salePercent);
				 saleEndDates.add(saleEndDate);
				 if (salesCourses.size() > 3) {
					 salesCourses = salesCourses.subList(0, 3); // Limit the list to 3 items
				 }
			 } else {
				 salePercents.add(null);
				 saleEndDates.add(null);
			 }
		 }
		 //Tính giá

		 List<Float> changePrices = new ArrayList<>();
		 for (int i = 0; i < salesCourses.size(); i++) {
			 Float salePercent = salePercents.get(i);
			 if (salePercent != null) {
				 Float originalPrice = salesCourses.get(i).getCourse().getPrice();
				 Float changePrice = originalPrice * (1 - salePercent / 100); // Calculate the change price
				 changePrices.add(changePrice);
			 } else {
				 changePrices.add(null);
			 }
		 }


		 //model giảm giá

		 model.addAttribute("changePrices", changePrices);
		 model.addAttribute("saleEndDates", saleEndDates);
		 //model khác
		 model.addAttribute("courses",courses);
		 model.addAttribute("sale",salesCourses);
		 //security login
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			 return "redirect:/admin/dashboard";
		 }
		 if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"))) {
			 return "/user/index.html";
		 }

	        return "/user/index.html";
	    }




}
