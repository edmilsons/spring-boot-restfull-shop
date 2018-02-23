package pl.rmitula.restfullshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.restfullshop.model.Category;
import pl.rmitula.restfullshop.model.User;
import pl.rmitula.restfullshop.model.dto.CategoryDto;
import pl.rmitula.restfullshop.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    public Long create(Category category) {
        return categoryRepository.save(category).getId();
    }
}
