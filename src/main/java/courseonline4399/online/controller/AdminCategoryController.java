package courseonline4399.online.controller;


import courseonline4399.online.model.Category;
import courseonline4399.online.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = {"/admin/category"})
    public String category(Model model){
        model.addAttribute("category", categoryService.findAll());
        return "admin/form/category-form";
    }
    @PostMapping(value={"admin/category/save/{categoryid}/{categoryname}"})
    public String saveCategory(@PathVariable("categoryid") Integer categoryId, @PathVariable("categoryname") String categoryName){
        try {
            Category category = categoryService.findById(categoryId);
            category.setCategoryname(categoryName);
            categoryService.update(category);
        }catch (Exception e){

        }

        return "redirect:/admin/category";
    }
    @PostMapping(value = {"/admin/category/create"})
    public String createCategory(@RequestParam("categoryname") String categoryName){
        Category category = new Category(); // Tạo một đối tượng Category mới
        category.setCategoryname(categoryName);
        categoryService.create(category);
        return "redirect:/admin/category";
    }
    @PostMapping(value = {"/admin/category/delete/{categoryId}"})
    public String deleteCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.delete(categoryId);
        return "redirect:/admin/category";
    }


}
