package com.example.filesorting.service.impl;

import com.example.filesorting.service.LineGeneratorService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class GeneratorString implements LineGeneratorService {

    @Override
    public String generate(int maxLineLength) {
        return RandomStringUtils.randomAlphanumeric(1, maxLineLength);
    }
}
