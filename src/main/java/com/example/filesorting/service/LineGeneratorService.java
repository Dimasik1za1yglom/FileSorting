package com.example.filesorting.service;

public interface LineGeneratorService {
    /**
     * Generates a string of the specified maximum length.
     * @param maxLineLength maximum string length, number of characters
     * @return generated string
     */
    String generate(int maxLineLength);
}
