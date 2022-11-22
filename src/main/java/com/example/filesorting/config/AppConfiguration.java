package com.example.filesorting.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class AppConfiguration {

    @Value("${source.file}")
    String sourceFile;

    @Value("${max.line.length}")
    Integer maxLineLength;

    @Value("${amount.line}")
    Integer amountLine;
}
