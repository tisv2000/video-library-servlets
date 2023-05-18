package com.github.tisv2000.videolibrary.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class EmailValidator {

    public boolean isValid(String email) {
        Pattern pattern = Pattern.compile("[\\w\\+]+@\\w+\\.[a-zA-Z]+");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
