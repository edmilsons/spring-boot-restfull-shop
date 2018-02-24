package pl.rmitula.restfullshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.restfullshop.exception.ConflictException;
import pl.rmitula.restfullshop.exception.NotFoundException;
import pl.rmitula.restfullshop.model.Category;
import pl.rmitula.restfullshop.repository.CategoryRepository;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findById(long id) {
        Category category = categoryRepository.findById(id);

        if(category != null) {
            return category;
        } else {
            throw new NotFoundException("Not found category with id: " + id);
        }
    }

    public Category findByName(String name) {
        Category category = categoryRepository.findByNameIgnoreCase(name);

        if(category != null) {
            return category;
        } else {
            throw new NotFoundException("Not found category with name: " + name);
        }
    }

    public void delete(long id) throws NotFoundException {
        Category category = categoryRepository.findById(id);

        if(category != null) {
            categoryRepository.delete(id);
        } else {
            throw new NotFoundException("Not found category with id: " + id);
        }
    }

    public Long create(Category category) {
        //TODO: Empty fields validation
        Category categoryName = categoryRepository.findByNameIgnoreCase(category.getName());

        if(categoryName == null) {
            return categoryRepository.save(category).getId();
        } else {
            throw new ConflictException("This category already exists.");
        }
    }

}
