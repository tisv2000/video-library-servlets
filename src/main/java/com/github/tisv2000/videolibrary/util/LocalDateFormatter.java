package com.github.tisv2000.videolibrary.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class LocalDateFormatter {

    public static final String PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalDate format(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public boolean isValid(String date) {
        try {
            format(date);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }
}
