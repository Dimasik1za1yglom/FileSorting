package com.example.filesorting;

import com.example.filesorting.service.impl.LineSorterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileRunner implements ApplicationRunner {

    private final LineSorterServiceImpl lineSorterServiceIml;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        lineSorterServiceIml.fileGenerateAndSorter();
    }
}
