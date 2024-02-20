package courseonline4399.online.rest;

import courseonline4399.online.model.Category;
import courseonline4399.online.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryRest {
    @Autowired
    CategoryService service;

    @GetMapping("rest/categories")
    public ResponseEntity<List<Category>> getAll(){
    List<Category> category= service.findAll();
    return  ResponseEntity.ok(category);

    }
}
