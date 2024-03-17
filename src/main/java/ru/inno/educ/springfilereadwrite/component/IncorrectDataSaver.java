package ru.inno.educ.springfilereadwrite.component;

import com.opencsv.CSVWriter;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.inno.educ.springfilereadwrite.entity.Login;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public class IncorrectDataSaver {

    @Value("${writer.path}")
    private Path filePath;
    @Value("${writer.fileName}")
    private String fileName;

    public void writeUnsavedDataToLog(List<Login> unsavedData) throws IOException {
        var file = new File(filePath.toString() + File.separator + fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {

            StatefulBeanToCsv<Login> sbc = new StatefulBeanToCsvBuilder<Login>(fileWriter)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(unsavedData);

        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }

    }
}
