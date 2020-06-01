package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findByPriceBetween(Optional<Integer> minPrice, Optional<Integer> maxPrice);

    List<Product> findByPriceGreaterThanEqual(Optional<Integer> minPrice);

    List<Product> findByPriceLessThanEqual(Optional<Integer> maxPrice);

}
