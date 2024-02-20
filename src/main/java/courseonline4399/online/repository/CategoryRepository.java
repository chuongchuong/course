package courseonline4399.online.repository;

import courseonline4399.online.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findBycategoryname(String name) ;
}
