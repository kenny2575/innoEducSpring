package ru.inno.educ.springfilereadwrite.service;

import java.util.List;

public interface FileHandlerService<T> {
    List<T> getDataFromFiles();
}
