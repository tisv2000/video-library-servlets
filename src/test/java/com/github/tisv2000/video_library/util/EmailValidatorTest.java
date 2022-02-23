package com.github.tisv2000.video_library.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailValidatorTest {

    @Test(dataProvider = "emailTestData")
    public void validEmailTest(String validEmail) {
        assertTrue(EmailValidator.isValid(validEmail));
    }


    @DataProvider(name = "emailTestData")
    public static Object[][] emailTestData() {
        return new Object[][] {
                {"test@test.com"},
                {"test+1234@test.com"},
                {"lksjdf34234@kj3.ru"},
        };
    }
}
