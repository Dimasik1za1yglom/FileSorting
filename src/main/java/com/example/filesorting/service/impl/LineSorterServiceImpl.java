package com.example.filesorting.service.impl;

import com.example.filesorting.config.AppConfiguration;
import com.example.filesorting.entite.Line;
import com.example.filesorting.service.FileService;
import com.example.filesorting.service.LineGeneratorService;
import com.example.filesorting.service.LineSorterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class LineSorterServiceImpl implements LineSorterService {

    private final FileService fileService;
    private final LineGeneratorService lineGeneratorService;
    private final AppConfiguration applicationConfig;

    public void fileGenerateAndSorter() {
        String sourceFile = applicationConfig.getSourceFile();
        File file = fileService.createFile(sourceFile);
        IntStream.range(0, applicationConfig.getAmountLine()).forEach(e -> fileService.writeToFile(sourceFile,
                lineGeneratorService.generate(applicationConfig.getMaxLineLength()), true));
        try {
            sortFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortFile(File file) throws IOException {
        Line[] arr = new Line[2];
        boolean unsorted = true;
        int li = 0;
        long countLine = getCountLine(file) - 1;
        while (unsorted) {
            unsorted = false;
            for (int i = 0; i < countLine; i++) {
                arr[0] = getLine(file, li);
                int step = arr[0].getValue().length() + 1;
                arr[1] = getLine(file, li + step);
                if (less(Line.BY_VALUE, arr[0], arr[1])) {
                    writeLine(file, Line.builder()
                            .value("%s\n%s\n".formatted(arr[1].getValue(), arr[0].getValue()))
                            .startSimvol(arr[0].getStartSimvol())
                            .build());
                    li += arr[1].getValue().length() + 1;
                    unsorted = true;
                } else {
                    li += step;
                }
            }
            li = 0;
        }
    }

    private static boolean less(Comparator<Line> comparator, Line v, Line w) {
        return comparator.compare(v, w) > 0;
    }

    private Long getCountLine(File file) throws IOException {
        return Files.lines(file.toPath()).count();

    }

    private Line getLine(File file, int li) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(li);
            return Line.builder()
                    .value(randomAccessFile.readLine())
                    .startSimvol(li)
                    .build();
        }
    }

    private void writeLine(File file, Line line) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.seek(line.getStartSimvol());
            randomAccessFile.writeBytes(line.getValue());
        }
    }


}
