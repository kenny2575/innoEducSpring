package ru.inno.educ.springfilereadwrite.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Component
public class LogWriter {

    @Value("${log.writePath}")
    private Path logFile;

    public void saveLog(String logData) throws IOException {
        Files.writeString(
                logFile,
                logData.concat(System.lineSeparator()),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND
        );
    }
}
