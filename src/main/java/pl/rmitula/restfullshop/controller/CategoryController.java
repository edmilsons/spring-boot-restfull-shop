package pl.rmitula.restfullshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.restfullshop.exception.NotFoundException;
import pl.rmitula.restfullshop.model.Category;
import pl.rmitula.restfullshop.model.dto.CategoryDto;
import pl.rmitula.restfullshop.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static pl.rmitula.restfullshop.controller.converter.Converter.fromCategoryDto;
import static pl.rmitula.restfullshop.controller.converter.Converter.toCategoryDto;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.getAll().stream().map((Category category) -> toCategoryDto(category)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable(name = "id") long id) throws NotFoundException {
       return toCategoryDto(categoryService.findById(id));
    }

    @GetMapping("/findByName/{name}")
    public CategoryDto findByName(@PathVariable(name = "name") String name) {
        return toCategoryDto(categoryService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.create(fromCategoryDto(categoryDto)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") long id, @RequestBody @Valid CategoryDto categoryDto) {
        categoryService.update(id, categoryDto.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) throws NotFoundException {
        categoryService.delete(id);
    }

}
