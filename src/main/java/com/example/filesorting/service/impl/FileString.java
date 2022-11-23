package com.example.filesorting.service.impl;

import com.example.filesorting.service.FileService;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileString implements FileService {
    @Override
    public File createFile(String fileName) {
        return new File(fileName);
    }

    @Override
    public void writeToFile(String pathAndFileName, String line, boolean append) {
        try (
                BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(pathAndFileName, append))) {
            bufferWriter.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
