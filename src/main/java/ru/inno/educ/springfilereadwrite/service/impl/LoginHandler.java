package ru.inno.educ.springfilereadwrite.service.impl;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;
import ru.inno.educ.springfilereadwrite.entity.Login;
import ru.inno.educ.springfilereadwrite.entity.User;
import ru.inno.educ.springfilereadwrite.enums.ApplicationTypeCode;
import ru.inno.educ.springfilereadwrite.model.LogLine;
import ru.inno.educ.springfilereadwrite.service.LoginHandlerService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoginHandler implements LoginHandlerService<LogLine> {

    @Override
    public Map<Boolean, List<Login>> divideLoginsByDateChecking(List<Login> logins){
        return logins.stream().collect(Collectors.groupingBy(this::checkDate));
    }
    @Override
    public boolean checkDate(Login login) {
        return login.getAccessDate() == null;
    }

    @Override
    public List<Login> parseSource(List<LogLine> source) {
        return source
                .stream()
                .map(line -> new Login(
                        0L,
                        line.getAccessDate(),
                        new User(0L, line.getUserName(), line.getFio()),
                        checkApplicationType(line.getApplication())))
                .collect(Collectors.toList());
    }

    private String checkApplicationType(String application) {
        var typeCode = application.toLowerCase();

        if (EnumUtils.isValidEnum(
                ApplicationTypeCode.class,
                typeCode
        ))
            return typeCode;
        else
            return "other: " + typeCode;
    }
}
