package ru.inno.educ.springfilereadwrite.component;


import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.inno.educ.springfilereadwrite.model.LogLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class Reader {

    @Value("${reader.path}")
    private String folder;

    public List<Path> getFilePaths() throws IOException {
        return Files.walk(Paths.get(folder))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }

    public List<LogLine> getDataFromFile(Path filePath) throws IOException {
        var bufferedReader = Files.newBufferedReader(filePath);

        return new CsvToBeanBuilder<LogLine>(bufferedReader)
                .withType(LogLine.class)
                .withSeparator('|')
                .build()
                .parse();
    }

}
