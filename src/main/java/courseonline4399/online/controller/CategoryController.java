package courseonline4399.online.controller;

import courseonline4399.online.model.Category;
import courseonline4399.online.model.Course;
import courseonline4399.online.service.CategoryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/category")
    public String finAllCate(Model model){
        Category item = new Category();
        model.addAttribute("listcate", item);

        List<Category> itemscate = categoryService.findAll();
        model.addAttribute("category", itemscate);
        return "/admin/form/cate-form";
    }
    @PostMapping("/category/create")
    public String createCate(Model model, @ModelAttribute("listcate") Category category){
        category.setCreateddate(new Date());
        categoryService.create(category);
        return "redirect:/category";
    }
    @GetMapping("/category/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/category";
    }
    @GetMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
        Category category = categoryService.findById(id);
        model.addAttribute("listcate", category);
        model.addAttribute("category", categoryService.findAll()); // Truyền danh sách category để hiển thị trong bảng
        return "/admin/form/cate-form";
    }
//    @PostMapping("/category/update")
//    public String update(Model model, @ModelAttribute("listcate") Category category){
//        category.setCreateddate(new Date());
//        categoryService.update(category);
//        return "redirect:/category";
//    }
//    @ModelAttribute("category")
//    public List<Category> getAllCourse(){
//        return categoryService.findAll();
//    }
}
