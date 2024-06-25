package ru.uvarov.ystu.kursach.repository;

import org.springframework.data.repository.CrudRepository;
import ru.uvarov.ystu.kursach.model.UserRole;


public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
}
