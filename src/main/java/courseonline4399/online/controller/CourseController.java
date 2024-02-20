package courseonline4399.online.controller;

import courseonline4399.online.model.Category;
import courseonline4399.online.model.Course;
import courseonline4399.online.model.SalesCourse;
import courseonline4399.online.service.CategoryService;
import courseonline4399.online.service.CourseService;
import courseonline4399.online.service.SalesCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CourseController {
		@Autowired
		CategoryService categoryService;
		@Autowired
		CourseService courseService;
	@Autowired
	SalesCourseService salesCourseService;

	int pageSize = 6; // Number of items displayed per page




	@GetMapping(value = {"/courses"})
		 public String khoahoc(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

		// Tìm khóa học có status = false
		Page<Course> coursePage = courseService.getAllInactiveCourses(PageRequest.of(page, pageSize));


		// Tìm khóa học giảm giá
		List<Course> courses = coursePage.getContent();
		// Fetch sales information for each course
		List<Float> salePercents = new ArrayList<>();
		List<Date> saleEndDates = new ArrayList<>();
		for (Course course : courses) {
			Pageable unlimitedPage = Pageable.unpaged();//unlimited retrieve
			// Replace pageSize with the desired size of the sales information page
			Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(course.getId(),unlimitedPage);
			// Get the content of the first page of sales information
			List<SalesCourse> salesCourses = salesCoursePage.getContent();
			if (!salesCourses.isEmpty()) {
				float salePercent = salesCourses.get(0).getSalepercent(); // Assuming you need the first sale percent
				Date saleEndDate = salesCourses.get(0).getEndsaledate();
				salePercents.add(salePercent);
				saleEndDates.add(saleEndDate);
			} else {
				salePercents.add(null); // or add a default value as needed
				saleEndDates.add(null);
			}
		}

		// Tính lại giá sau khi giảm giá
		List<Float> changePrices = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			Float salePercent = salePercents.get(i);
			if (salePercent != null) {
				Float originalPrice = courses.get(i).getPrice();
				Float changePrice = originalPrice * (1 - salePercent / 100); // Calculate the change price
				changePrices.add(changePrice);
			} else {
				changePrices.add(null);
			}
		}


		// Fetch active courses
		model.addAttribute("paginationFragment", "default");
		model.addAttribute("salePercents", salePercents);
		model.addAttribute("changePrices", changePrices);
		model.addAttribute("saleEndDates", saleEndDates);
		model.addAttribute("courses", coursePage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", coursePage.getTotalPages());

		List<Course> top3Course = courseService.findTop3().stream()
				.limit(3)
				.collect(Collectors.toList());
		model.addAttribute("top3Course" , top3Course);

		return "user/courses.html";
		 }
	@ModelAttribute("cates")
	public List<Category> getCategories() {
		return categoryService.findAll();
	}

	///////////////////////Tìm khóa học

	@GetMapping(value = {"/search_courses"})
	public String searchCourse(Model model,
						  @RequestParam(name = "page", defaultValue = "0") int page,
						  @RequestParam(name = "name", defaultValue = "") String name,
						  @RequestParam(name = "categoryId", defaultValue = "0") Integer categoryId) {
		Page<Course> coursePage;

		if (name != null && categoryId != 0	) {
			// If name and categoryId are provided, perform a search
			 coursePage = courseService.findCoursesByCoursenameAndCategoryAndStatus(name, categoryId, PageRequest.of(page, pageSize));

		}else if(name !=null){
			 coursePage= courseService.findCoursesByCoursenameAndStatus(name,PageRequest.of(page, pageSize));

		}
		else {
			// Otherwise, retrieve all courses
			 coursePage = courseService.getAllInactiveCourses(PageRequest.of(page, pageSize));

		}
// Tìm khóa học giảm giá
		List<Course> courses = coursePage.getContent();
		// Fetch sales information for each course
		List<Float> salePercents = new ArrayList<>();
		List<Date> saleEndDates = new ArrayList<>();
		for (Course course : courses) {
			Pageable unlimitedPage = Pageable.unpaged();//unlimited retrieve
			// Replace pageSize with the desired size of the sales information page
			Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(course.getId(),unlimitedPage);
			// Get the content of the first page of sales information
			List<SalesCourse> salesCourses = salesCoursePage.getContent();
			if (!salesCourses.isEmpty()) {
				float salePercent = salesCourses.get(0).getSalepercent(); // Assuming you need the first sale percent
				Date saleEndDate = salesCourses.get(0).getEndsaledate();
				salePercents.add(salePercent);
				saleEndDates.add(saleEndDate);
			} else {
				salePercents.add(null); // or add a default value as needed
				saleEndDates.add(null);
			}
		}

		// Tính lại giá sau khi giảm giá
		List<Float> changePrices = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			Float salePercent = salePercents.get(i);
			if (salePercent != null) {
				Float originalPrice = courses.get(i).getPrice();
				Float changePrice = originalPrice * (1 - salePercent / 100); // Calculate the change price
				changePrices.add(changePrice);
			} else {
				changePrices.add(null);
			}
		}


		// Fetch active courses

		model.addAttribute("salePercents", salePercents);
		model.addAttribute("changePrices", changePrices);
		model.addAttribute("saleEndDates", saleEndDates);


		model.addAttribute("name", name);
		model.addAttribute("categoryId", categoryId);

		model.addAttribute("courses", coursePage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", coursePage.getTotalPages());

		model.addAttribute("paginationFragment", "search");

		return "user/courses.html";
	}

	///////////////////////////////Phân giá
	@GetMapping("/sorted-by-price-asc")
	public String getCourseSortedByPriceAsc(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {


		Page<Course> coursePage = courseService.getAllCourseSortedByPriceAsc(PageRequest.of(page, pageSize));

		// Tìm khóa học giảm giá
		List<Course> courses = coursePage.getContent();
		// Fetch sales information for each course
		List<Float> salePercents = new ArrayList<>();
		List<Date> saleEndDates = new ArrayList<>();
		for (Course course : courses) {
			Pageable unlimitedPage = Pageable.unpaged();//unlimited retrieve
			// Replace pageSize with the desired size of the sales information page
			Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(course.getId(),unlimitedPage);
			// Get the content of the first page of sales information
			List<SalesCourse> salesCourses = salesCoursePage.getContent();
			if (!salesCourses.isEmpty()) {
				float salePercent = salesCourses.get(0).getSalepercent(); // Assuming you need the first sale percent
				Date saleEndDate = salesCourses.get(0).getEndsaledate();
				salePercents.add(salePercent);
				saleEndDates.add(saleEndDate);
			} else {
				salePercents.add(null); // or add a default value as needed
				saleEndDates.add(null);
			}
		}

		// Tính lại giá sau khi giảm giá
		List<Float> changePrices = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			Float salePercent = salePercents.get(i);
			if (salePercent != null) {
				Float originalPrice = courses.get(i).getPrice();
				Float changePrice = originalPrice * (1 - salePercent / 100); // Calculate the change price
				changePrices.add(changePrice);
			} else {
				changePrices.add(null);
			}
		}


		// Fetch active courses

		model.addAttribute("salePercents", salePercents);
		model.addAttribute("changePrices", changePrices);
		model.addAttribute("saleEndDates", saleEndDates);
		model.addAttribute("paginationFragment", "asc");
		model.addAttribute("courses", coursePage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", coursePage.getTotalPages());



		List<Course> top3Course = courseService.findTop3().stream()
				.limit(3)
				.collect(Collectors.toList());
		model.addAttribute("top3Course" , top3Course);
		return "user/courses.html";
	}
	@GetMapping("/sorted-by-price-desc")
	public String getCourseSortedByPriceDesc(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

		Page<Course> coursePage = courseService.getAllCourseSortedByPriceDesc(PageRequest.of(page, pageSize));

		// Tìm khóa học giảm giá
		List<Course> courses = coursePage.getContent();
		// Fetch sales information for each course
		List<Float> salePercents = new ArrayList<>();
		List<Date> saleEndDates = new ArrayList<>();
		for (Course course : courses) {
			Pageable unlimitedPage = Pageable.unpaged();//unlimited retrieve
			// Replace pageSize with the desired size of the sales information page
			Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(course.getId(),unlimitedPage);
			// Get the content of the first page of sales information
			List<SalesCourse> salesCourses = salesCoursePage.getContent();
			if (!salesCourses.isEmpty()) {
				float salePercent = salesCourses.get(0).getSalepercent(); // Assuming you need the first sale percent
				Date saleEndDate = salesCourses.get(0).getEndsaledate();
				salePercents.add(salePercent);
				saleEndDates.add(saleEndDate);
			} else {
				salePercents.add(null); // or add a default value as needed
				saleEndDates.add(null);
			}
		}

		// Tính lại giá sau khi giảm giá
		List<Float> changePrices = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			Float salePercent = salePercents.get(i);
			if (salePercent != null) {
				Float originalPrice = courses.get(i).getPrice();
				Float changePrice = originalPrice * (1 - salePercent / 100); // Calculate the change price
				changePrices.add(changePrice);
			} else {
				changePrices.add(null);
			}
		}


		// Fetch active courses

		model.addAttribute("salePercents", salePercents);
		model.addAttribute("changePrices", changePrices);
		model.addAttribute("saleEndDates", saleEndDates);
		model.addAttribute("paginationFragment", "desc");
		model.addAttribute("courses", coursePage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", coursePage.getTotalPages());

		List<Course> top3Course = courseService.findTop3().stream()
				.limit(3)
				.collect(Collectors.toList());
		model.addAttribute("top3Course" , top3Course);
		return "user/courses.html";
	}
	@GetMapping("/find-by-price")
	public String getCourseByPriceBetween(Model model,@RequestParam( value = "minprice" ,defaultValue = "0.0") Double minprice, @RequestParam(value = "maxprice",defaultValue = "0.0") Double maxprice, @RequestParam(name = "page", defaultValue = "0") int page) {
		if (minprice ==null){
			minprice=0.0;
		}
		Page<Course> coursePage = courseService.findByPriceBetween(minprice,maxprice,PageRequest.of(page, pageSize));

		// Tìm khóa học giảm giá
		List<Course> courses = coursePage.getContent();
		// Fetch sales information for each course
		List<Float> salePercents = new ArrayList<>();
		List<Date> saleEndDates = new ArrayList<>();
		for (Course course : courses) {
			Pageable unlimitedPage = Pageable.unpaged();//unlimited retrieve
			// Replace pageSize with the desired size of the sales information page
			Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(course.getId(),unlimitedPage);
			// Get the content of the first page of sales information
			List<SalesCourse> salesCourses = salesCoursePage.getContent();
			if (!salesCourses.isEmpty()) {
				float salePercent = salesCourses.get(0).getSalepercent(); // Assuming you need the first sale percent
				Date saleEndDate = salesCourses.get(0).getEndsaledate();
				salePercents.add(salePercent);
				saleEndDates.add(saleEndDate);
			} else {
				salePercents.add(null); // or add a default value as needed
				saleEndDates.add(null);
			}
		}

		// Tính lại giá sau khi giảm giá
		List<Float> changePrices = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			Float salePercent = salePercents.get(i);
			if (salePercent != null) {
				Float originalPrice = courses.get(i).getPrice();
				Float changePrice = originalPrice * (1 - salePercent / 100); // Calculate the change price
				changePrices.add(changePrice);
			} else {
				changePrices.add(null);
			}
		}


		// Fetch active courses

		model.addAttribute("salePercents", salePercents);
		model.addAttribute("changePrices", changePrices);
		model.addAttribute("saleEndDates", saleEndDates);
		model.addAttribute("paginationFragment", "findByPrice");
		model.addAttribute("minprice",minprice);
		model.addAttribute("maxprice",maxprice);
		model.addAttribute("courses", coursePage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", coursePage.getTotalPages());

		List<Course> top3Course = courseService.findTop3().stream()
				.limit(3)
				.collect(Collectors.toList());
		model.addAttribute("top3Course" , top3Course);
		return "user/courses.html";
	}
	//Khóa học khuyến mãi

	@GetMapping(value = {"/courses/sales"})
	public String sale(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

		// Tìm khóa học có status = false
		Page<SalesCourse> coursePage = salesCourseService.getSalesCoursesWithEndSaleDateAfterCurrentDateAndStatusSaleIsFalse(PageRequest.of(page, pageSize));


		// Tìm khóa học giảm giá
		List<SalesCourse> courses = coursePage.getContent();
		// Fetch sales information for each course
		List<Float> salePercents = new ArrayList<>();
		List<Date> saleEndDates = new ArrayList<>();
		for (SalesCourse course : courses) {

			// Replace pageSize with the desired size of the sales information page
			Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(course.getCourse().getId(),Pageable.unpaged());
			// Get the content of the first page of sales information
			List<SalesCourse> salesCourses = salesCoursePage.getContent();
			if (!salesCourses.isEmpty()) {
				float salePercent = salesCourses.get(0).getSalepercent(); // Assuming you need the first sale percent
				Date saleEndDate = salesCourses.get(0).getEndsaledate();
				salePercents.add(salePercent);
				saleEndDates.add(saleEndDate);
			} else {
				salePercents.add(null); // or add a default value as needed
				saleEndDates.add(null);
			}
		}

		// Tính lại giá sau khi giảm giá
		List<Float> changePrices = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			Float salePercent = salePercents.get(i);
			if (salePercent != null) {
				Float originalPrice = courses.get(i).getCourse().getPrice();
				Float changePrice = originalPrice * (1 - salePercent / 100); // Calculate the change price
				changePrices.add(changePrice);
			} else {
				changePrices.add(null);
			}
		}


		// Fetch active courses
		model.addAttribute("paginationFragment", "sales");
		model.addAttribute("salePercents", salePercents);
		model.addAttribute("changePrices", changePrices);
		model.addAttribute("saleEndDates", saleEndDates);
		model.addAttribute("courses", coursePage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", coursePage.getTotalPages());

		List<Course> top3Course = courseService.findTop3().stream()
				.limit(3)
				.collect(Collectors.toList());
		model.addAttribute("top3Course" , top3Course);

		return "user/courses.html";
	}

}
