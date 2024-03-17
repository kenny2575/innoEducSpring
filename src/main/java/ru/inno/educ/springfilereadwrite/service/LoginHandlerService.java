package ru.inno.educ.springfilereadwrite.service;

import ru.inno.educ.springfilereadwrite.entity.Login;

import java.util.List;
import java.util.Map;

public interface LoginHandlerService<T> {

    Map<Boolean, List<Login>> divideLoginsByDateChecking(List<Login> logins);
    boolean checkDate(Login login);

    List<Login> parseSource (List<T> source);


}
