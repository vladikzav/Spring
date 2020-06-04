package ru.geekbrains.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    Page<User> findByNameContaining(Optional<String> search, Pageable pageable);

    Page<User> findByAgeBetween(Optional<Integer> minAge, Optional<Integer> maxAge, Pageable pageable);

    Page<User> findByAgeGreaterThanEqual(Optional<Integer> minAge, Pageable pageable);

    Page<User> findByAgeLessThanEqual(Optional<Integer> maxAge, Pageable pageable);

}
