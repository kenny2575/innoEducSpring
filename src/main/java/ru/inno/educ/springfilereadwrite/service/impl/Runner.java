package ru.inno.educ.springfilereadwrite.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import ru.inno.educ.springfilereadwrite.component.DataBaseSaver;
import ru.inno.educ.springfilereadwrite.component.IncorrectDataSaver;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {
    private final FileHandler fileHandler;
    private final UserHandler userHandler;
    private final LoginHandler loginHandler;
    private final DataBaseSaver saver;
    private final IncorrectDataSaver dataSaver;
    @Override
    public void run(ApplicationArguments args){

        var readData = fileHandler.getDataFromFiles();
        var users = userHandler.parseSource(readData);

        saver.saveNewUsers(users);

        var logins = loginHandler.parseSource(readData);

        var mapOfLog = loginHandler.divideLoginsByDateChecking(logins);

        mapOfLog.forEach((k, v) ->
        {
            if (k) {
                saver.saveLogLines(v);
            } else {
                try {
                    dataSaver.writeUnsavedDataToLog(v);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
