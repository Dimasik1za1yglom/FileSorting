package com.example.filesorting.service;

import java.io.File;

public interface FileService {

    /**
     * создание файла
     *
     * @param fileName путь к файлу
     * @return сам файл
     */
    File createFile(String fileName);

    /**
     * Записать строку в файл
     *
     * @param pathAndFileName название файла для записи и путь
     * @param line            добавляемая строка
     * @param append          признак дозаписи файла
     */
    void writeToFile(String pathAndFileName, String line, boolean append);
}
