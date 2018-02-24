package pl.rmitula.restfullshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.restfullshop.exception.BadRequestException;
import pl.rmitula.restfullshop.exception.ConflictException;
import pl.rmitula.restfullshop.exception.NotFoundException;
import pl.rmitula.restfullshop.model.Category;
import pl.rmitula.restfullshop.model.Product;
import pl.rmitula.restfullshop.repository.ProductRepository;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product findById(long id) throws NotFoundException {
        Product product = productRepository.findById(id);

        if(product != null) {
            return product;
        } else {
            throw new NotFoundException("Not found product with id: " + id);
        }
    }

    public Product findByName(String name) throws NotFoundException {
        Product product = productRepository.findByNameIgnoreCase(name);

        if(product != null) {
            return product;
        } else {
            throw new NotFoundException("Not found product with name: " + name);
        }
    }

    public List<Product> findByCategoryId(Long categoryId) {
        Category category = categoryService.findById(categoryId);

        if (category != null) {
            List<Product> products = productRepository.findByCategory(category);

            if(!products.isEmpty()) {
                return products;

            } else {
                throw new NotFoundException("Not found products for category with id: " + categoryId);
            }
        } else {
            throw  new NotFoundException("Not found category with id: " + categoryId);
        }
    }

    public Long create(Product product) throws ConflictException, BadRequestException {
        //TODO: Empty fields validation
        Product name = productRepository.findByNameIgnoreCase(product.getName());

        if(name == null) {
            try {
                return productRepository.save(product).getId();
            } catch (ConstraintViolationException e) {
                throw new BadRequestException();
            }
        } else {
            throw new ConflictException("This name is already associated with a different product.");
        }
    }

    public void delete(long id) throws NotFoundException {
        Product product = productRepository.findById(id);

        if(product != null) {
            productRepository.delete(id);
        } else {
            throw new NotFoundException("Not found product with id: " + id);
        }
    }

}
