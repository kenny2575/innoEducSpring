package ru.inno.educ.springfilereadwrite.component;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.list.SetUniqueList;
import org.springframework.stereotype.Component;
import ru.inno.educ.springfilereadwrite.entity.Login;
import ru.inno.educ.springfilereadwrite.entity.User;
import ru.inno.educ.springfilereadwrite.repository.LoginRepository;
import ru.inno.educ.springfilereadwrite.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class DataBaseSaver {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public void saveNewUsers(Iterable<User> users){
        var usersForSave = new HashMap<String, User>();
        users.forEach(
                user -> {
                    var savedUser = userRepository.getByUserName(user.getUserName());
                    if (savedUser.isEmpty()){
                        usersForSave.putIfAbsent(user.getUserName(), user);
                    }
                }
        );
        userRepository.saveAll(usersForSave.values());
    }

    public void saveLogLines(Iterable<Login> logins){
        loginRepository.saveAll(logins);
    }
}
