package ru.inno.educ.springfilereadwrite.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.inno.educ.springfilereadwrite.entity.Login;
import ru.inno.educ.springfilereadwrite.entity.User;
import ru.inno.educ.springfilereadwrite.repository.LoginRepository;
import ru.inno.educ.springfilereadwrite.repository.UserRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DataBaseSaver {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public void saveNewUsers(Iterable<User> users){
        var usersForSave = new ArrayList<User>();
        users.forEach(
                user -> {
                    var savedUser = userRepository.getByUserName(user.getUserName());
                    if (savedUser.isEmpty()){
                        usersForSave.add(user);
                    }
                }
        );
        userRepository.saveAll(usersForSave);
    }

    public void saveLogLines(Iterable<Login> logins){
        loginRepository.saveAll(logins);
    }
}
