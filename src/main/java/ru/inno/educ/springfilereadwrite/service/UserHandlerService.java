package ru.inno.educ.springfilereadwrite.service;

import ru.inno.educ.springfilereadwrite.entity.User;

import java.util.List;

public interface UserHandlerService<T> {
    List<User> parseSource(List<T> source);
}
