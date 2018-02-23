package pl.rmitula.restfullshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.restfullshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Product findById(long id);

    Product findByNameIgnoreCase(String name);
}
