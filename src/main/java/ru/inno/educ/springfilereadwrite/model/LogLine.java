package ru.inno.educ.springfilereadwrite.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogLine {

    @CsvBindByPosition(position = 0)
    String userName;

    @CsvBindByPosition(position = 1)
    String fio;

    @CsvBindByPosition(position = 2)
    @CsvDate(value = "yyyy-MM-dd'T'HH:mm:ss")
    Date accessDate;

    @CsvBindByPosition(position = 3)
    String application;

}
