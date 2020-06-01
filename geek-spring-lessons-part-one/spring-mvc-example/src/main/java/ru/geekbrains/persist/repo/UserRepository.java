package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    List<User> findByAgeBetween(Optional<Integer> minAge, Optional<Integer> maxAge);

    List<User> findByAgeGreaterThanEqual(Optional<Integer> minAge);

    List<User> findByAgeLessThanEqual(Optional<Integer> maxAge);

}
