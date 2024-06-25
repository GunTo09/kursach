package ru.uvarov.ystu.kursach.repository;

import org.springframework.data.repository.CrudRepository;
import ru.uvarov.ystu.kursach.model.User;


import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
