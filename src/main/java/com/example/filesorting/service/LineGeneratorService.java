package com.example.filesorting.service;

public interface LineGeneratorService {
    /**
     * Генерация строки в документ
     * @param maxLineLength максимальная длина строки
     * @return строку
     */
    String generate(int maxLineLength);
}
