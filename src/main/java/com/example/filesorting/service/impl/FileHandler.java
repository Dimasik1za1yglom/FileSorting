package com.example.filesorting.service.impl;

import com.example.filesorting.config.AppConfiguration;
import com.example.filesorting.entity.Line;
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
public class FileHandler implements LineSorterService {

    private final FileService fileService;
    private final LineGeneratorService lineGeneratorService;
    private final AppConfiguration applicationConfig;

    public void fileGenerateAndSorter() {
        String sourceFile = applicationConfig.getSourceFile();
        File file = fileService.createFile(sourceFile);
        IntStream.range(0, applicationConfig.getAmountLine())
                .forEach(e -> fileService.writeToFile(sourceFile,
                        lineGeneratorService.generate(applicationConfig.getMaxLineLength()),
                        true));
        try {
            sortFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortFile(File file) throws IOException {
        Line[] linesSort = new Line[2];
        boolean unsorted = true;
        int placePointer = 0;
        long countLine = getCountLine(file) - 1;
        while (unsorted) {
            unsorted = false;
            for (int i = 0; i < countLine; i++) {
                linesSort[0] = getLine(file, placePointer);
                int step = linesSort[0].getValue().length() + 1;
                linesSort[1] = getLine(file, placePointer + step);
                if (less(Line.BY_VALUE, linesSort[0], linesSort[1])) {
                    writeLine(file, Line.builder()
                            .value("%s\n%s\n".formatted(linesSort[1].getValue(), linesSort[0].getValue()))
                            .startSymbol(linesSort[0].getStartSymbol())
                            .build());
                    placePointer += linesSort[1].getValue().length() + 1;
                    unsorted = true;
                } else {
                    placePointer += step;
                }
            }
            placePointer = 0;
        }
    }

    private boolean less(Comparator<Line> comparator, Line v, Line w) {
        return comparator.compare(v, w) > 0;
    }

    private Long getCountLine(File file) throws IOException {
        return Files.lines(file.toPath()).count();

    }

    private Line getLine(File file, int li) throws IOException {
        try (var randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(li);
            return Line.builder()
                    .value(randomAccessFile.readLine())
                    .startSymbol(li)
                    .build();
        }
    }

    private void writeLine(File file, Line line) throws IOException {
        try (var randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.seek(line.getStartSymbol());
            randomAccessFile.writeBytes(line.getValue());
        }
    }


}
