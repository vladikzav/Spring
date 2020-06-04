package ru.geekbrains.springboot.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springboot.persist.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    Page<Product> findByNameContaining(Optional<String> search, Pageable pageable);

    Page<Product> findByPriceBetween(Optional<Integer> minPrice, Optional<Integer> maxPrice, Pageable pageable);

    Page<Product> findByPriceGreaterThanEqual(Optional<Integer> minPrice, Pageable pageable);

    Page<Product> findByPriceLessThanEqual(Optional<Integer> maxPrice, Pageable pageable);

}
