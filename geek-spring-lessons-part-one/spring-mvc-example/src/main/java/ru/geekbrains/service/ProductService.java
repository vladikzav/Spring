package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

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

    @Transactional(readOnly = true)
    public List<Product> filterByPrice(Optional<Integer> minPrice, Optional<Integer> maxPrice) {

        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> filterByMinPrice(Optional<Integer> minPrice) {
        return repository.findByPriceGreaterThanEqual(minPrice);
    }

    @Transactional(readOnly = true)
    public List<Product> filterByMaxPrice(Optional<Integer> maxPrice) {
        return repository.findByPriceLessThanEqual(maxPrice);
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
