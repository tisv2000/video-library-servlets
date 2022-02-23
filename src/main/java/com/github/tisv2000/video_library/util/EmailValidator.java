package com.github.tisv2000.video_library.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class EmailValidator {

    public boolean isValid(String email) {
        // TODO почему не работает???
        Pattern pattern = Pattern.compile("[\\w]]@[a-zA-Z]\\.[a-zA-Z]");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
