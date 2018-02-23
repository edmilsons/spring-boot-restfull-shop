package pl.rmitula.restfullshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.restfullshop.model.Category;
import pl.rmitula.restfullshop.model.Product;
import pl.rmitula.restfullshop.model.dto.ProductDto;
import pl.rmitula.restfullshop.service.CategoryService;
import pl.rmitula.restfullshop.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static pl.rmitula.restfullshop.controller.converter.Converter.fromProductDto;
import static pl.rmitula.restfullshop.controller.converter.Converter.toProductDto;

@RequestMapping("api/products")
@RestController
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ProductDto> get() {
        return productService.getProducts().stream().map((Product product) -> toProductDto(product)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public HttpEntity<ProductDto> findById(@PathVariable(name = "id") long id) {
        Product product = productService.findById(id);

        if (product != null) {
            return new ResponseEntity<>(toProductDto(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByName/{name}")
    public HttpEntity<ProductDto> findByName(@PathVariable(name = "name") String name) {
        Product product = productService.findByName(name);

        if (product != null) {
            return new ResponseEntity<>(toProductDto(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public HttpEntity<Long> create(@RequestBody @Valid ProductDto productDto) {
        Category category = categoryService.findById(productDto.getCategory());

        if(category != null) {
            return new ResponseEntity<>(productService.create(fromProductDto(productDto, category)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
