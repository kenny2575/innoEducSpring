package ru.inno.educ.springfilereadwrite.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.inno.educ.springfilereadwrite.component.Reader;
import ru.inno.educ.springfilereadwrite.model.LogLine;
import ru.inno.educ.springfilereadwrite.service.FileHandlerService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileHandler implements FileHandlerService<LogLine> {


    private final Reader reader;

    @Override
    public List<LogLine> getDataFromFiles() {
        log.info("readAndWrite {}", reader.getFolder());
        var logLines = new ArrayList<LogLine>();
        try{
            for(Path file : reader.getFilePaths()){
                logLines.addAll(reader.getDataFromFile(file));
            }
        }catch (IOException e){
            log.warn("exception while reading folder: {}", e.getMessage());
        }
        return logLines;
    }
}
