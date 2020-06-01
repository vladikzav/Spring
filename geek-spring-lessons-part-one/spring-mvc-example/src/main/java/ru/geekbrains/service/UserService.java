package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.persist.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<User> filterByAge(Optional<Integer> minAge, Optional<Integer> maxAge) {

        return repository.findByAgeBetween(minAge, maxAge);
    }

    @Transactional(readOnly = true)
    public List<User> filterByMinAge(Optional<Integer> minAge) {
        return repository.findByAgeGreaterThanEqual(minAge);
    }

    @Transactional(readOnly = true)
    public List<User> filterByMaxAge(Optional<Integer> maxAge) {
        return repository.findByAgeLessThanEqual(maxAge);
    }

    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }
}
