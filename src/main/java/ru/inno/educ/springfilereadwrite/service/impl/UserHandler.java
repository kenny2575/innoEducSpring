package ru.inno.educ.springfilereadwrite.service.impl;

import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;
import ru.inno.educ.springfilereadwrite.entity.User;
import ru.inno.educ.springfilereadwrite.model.LogLine;
import ru.inno.educ.springfilereadwrite.service.UserHandlerService;
import ru.inno.educ.springfilereadwrite.utils.LogTransformation;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserHandler implements UserHandlerService<LogLine> {

    @Override
    @LogTransformation
    public List<User> parseSource(List<LogLine> source) {

        return source
                .stream()
                .map(line -> new User(0L, line.getUserName(), WordUtils.capitalizeFully(line.getFio())))
                .collect(Collectors.toList());
    }
}
