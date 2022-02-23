package com.github.tisv2000.video_library.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailValidatorTest {

    @Test
    public void validEmailTest() {
        var validEmail = "test@test.com";
        var result = Integer.valueOf(validEmail);
        assertTrue(EmailValidator.isValid(validEmail));
    }
}
