package ru.inno.educ.springfilereadwrite.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.inno.educ.springfilereadwrite.entity.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {
}
