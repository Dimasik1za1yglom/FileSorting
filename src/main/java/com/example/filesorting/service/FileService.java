package com.example.filesorting.service;

import java.io.File;

public interface FileService {

    /**
     * Creating a file using the received path
     * @param fileName file path
     * @return an object of the File class of the created file
     */
    File createFile(String fileName);

    /**
     * Writes a string to the file using the received path.
     * You can overwrite the file or add a new line
     * @param pathAndFileName The path to the file to write to
     * @param line            line to write
     * @param append          The status of the file entry.
     *                        True - append to an existing file, false - Overwrite an existing file
     */
    void writeToFile(String pathAndFileName, String line, boolean append);
}
