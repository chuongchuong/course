package courseonline4399.online.service;

import courseonline4399.online.model.Category;
import courseonline4399.online.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CRUD<Category,Integer> {
    @Autowired
    CategoryRepository repository;
    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public void delete(Integer integer) {
        repository.deleteById(integer);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(Integer integer) {
        return repository.findById(integer).get();
    }
    public Category findByname(String name) {
        return repository.findBycategoryname(name);
    }
}
