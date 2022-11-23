package com.example.filesorting.service;

import java.io.File;
import java.io.IOException;

public interface LineSorterService {

    /**
     * Sorts the lines in the file
     * @param file the file in which to sort the rows
     */
    void sortFile(File file) throws IOException;
}
