package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.Gender;
import com.github.tisv2000.videolibrary.entity.Role;
import com.github.tisv2000.videolibrary.entity.User;
import com.github.tisv2000.videolibrary.util.LocalDateFormatter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserDaoIntegrationTest {

    private final UserDao userDao = UserDao.getInstance();

    private static final String EMAIL = LocalDateTime.now() + "test@test.test";
    private static final String NOT_EXISTING_EMAIL = "wrong@test.test";
    private static final LocalDate BIRTHDAY = LocalDateFormatter.format("2000-01-01");
    private static final String USER_NAME = "Mr. Test";
    private static final String PASSWORD = "password";
    private static final String IMAGE = "image";
    private static final Role ROLE = Role.USER;
    private static final Gender GENDER = Gender.MALE;

    @Test
    public void saveTest() {
        // GIVEN
        var user = User.builder()
                .name(USER_NAME)
                .birthday(BIRTHDAY)
                .password(PASSWORD)
                .email(EMAIL)
                .image(IMAGE)
                .role(ROLE)
                .gender(GENDER)
                .build();

        // WHEN
        userDao.save(user);

        // THEN
        assertNotNull(user.getId());
    }

    @Test(dependsOnMethods = {"saveTest"})
    public void findByExistingEmailTest() {
        // GIVEN
        // WHEN
        var user = userDao.findByEmail(EMAIL);

        // THEN
        assertTrue(user.isPresent());
        assertEquals(user.get().getName(), USER_NAME);
        assertEquals(user.get().getBirthday(), BIRTHDAY);
        assertEquals(user.get().getEmail(), EMAIL);
        assertEquals(user.get().getImage(), IMAGE);
        assertEquals(user.get().getRole(), ROLE);
        assertEquals(user.get().getGender(), GENDER);
    }

    @Test
    public void findByNotExistingEmailTest() {
        // GIVEN
        // WHEN
        var user = userDao.findByEmail(NOT_EXISTING_EMAIL);

        // THEN
        assertTrue(user.isEmpty());
    }

    @Test(dependsOnMethods = {"saveTest"})
    public void findByEmailAndPasswordPositiveTest() {
        // GIVEN
        // WHEN
        var user = userDao.findByEmailAndPassword(EMAIL, PASSWORD);

        // THEN
        assertTrue(user.isPresent());
        assertEquals(user.get().getName(), USER_NAME);
        assertEquals(user.get().getBirthday(), BIRTHDAY);
        assertEquals(user.get().getEmail(), EMAIL);
        assertEquals(user.get().getImage(), IMAGE);
        assertEquals(user.get().getRole(), ROLE);
        assertEquals(user.get().getGender(), GENDER);
    }

    @Test(dependsOnMethods = {"saveTest"}, dataProvider = "findByEmailAndPasswordNegativeDataProvider")
    public void findByEmailAndPasswordNegativeTest(String email, String password) {
        // GIVEN
        // WHEN
        var user = userDao.findByEmailAndPassword(email, password);

        // THEN
        assertTrue(user.isEmpty());
    }

    @DataProvider(name = "findByEmailAndPasswordNegativeDataProvider")
    public static Object[][] findByEmailAndPasswordNegativeDataProvider() {
        return new Object[][]{
                {EMAIL, "wrong password"},
                {"wrong@test.test", PASSWORD}
        };
    }
}
