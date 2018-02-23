package pl.rmitula.restfullshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.restfullshop.model.Category;
import pl.rmitula.restfullshop.model.dto.CategoryDto;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(long id);

    Category findByNameIgnoreCase(String name);
}
