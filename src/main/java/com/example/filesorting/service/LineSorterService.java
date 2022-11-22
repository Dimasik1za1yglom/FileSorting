package com.example.filesorting.service;

import java.io.File;
import java.io.IOException;

public interface LineSorterService {

    /**
     * Сортировать файл
     * @param file самй файл
     */
    void sortFile(File file) throws IOException;
}
