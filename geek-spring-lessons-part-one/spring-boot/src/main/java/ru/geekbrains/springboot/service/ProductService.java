package ru.geekbrains.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.springboot.persist.entity.Product;
import ru.geekbrains.springboot.persist.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }


    public Page<Product> filterByPrice(Optional<Integer> minPrice, Optional<Integer> maxPrice, Optional<String> search, Pageable pageable) {

        if(search.isPresent()){
            return repository.findByNameContaining(search, pageable);
        }

        if (!minPrice.isPresent() && !maxPrice.isPresent()) {
            return repository.findAll(pageable);
        }
        if (minPrice.isPresent() && !maxPrice.isPresent()) {
            return repository.findByPriceGreaterThanEqual(minPrice, pageable);
        }
        if (minPrice == null) {
            return repository.findByPriceLessThanEqual(maxPrice, pageable);
        }
        return repository.findByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }
}
