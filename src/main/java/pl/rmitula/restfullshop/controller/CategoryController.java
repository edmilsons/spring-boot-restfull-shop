package pl.rmitula.restfullshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public HttpEntity<CategoryDto> findById(@PathVariable(name = "id") long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return new ResponseEntity<>(toCategoryDto(category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByName/{name}")
    public HttpEntity<CategoryDto> findByName(@PathVariable(name = "name") String name) {
        Category category = categoryService.findByName(name);
        if (category != null) {
            return new ResponseEntity<>(toCategoryDto(category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public HttpEntity<Long> create(@RequestBody @Valid CategoryDto categoryDto) {
        Category category = categoryService.findByName(categoryDto.getName());

        if (category == null) {
            return new ResponseEntity<>(categoryService.create(fromCategoryDto(categoryDto)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


}
