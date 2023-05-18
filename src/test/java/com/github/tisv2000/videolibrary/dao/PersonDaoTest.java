package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.dto.PersonFilterDto;
import com.github.tisv2000.videolibrary.entity.Person;
import com.github.tisv2000.videolibrary.util.LocalDateFormatter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PersonDaoTest {

    private final PersonDao underTest = PersonDao.getInstance();

    @Test
    public void testFindAll() {
        var persons = underTest.findAll();
        assertTrue(persons.size() > 18);
    }

    @Test
    public void findByIdPositiveTest() {
        var maybeResult = underTest.findById(1);
        assertTrue(maybeResult.isPresent());
        assertEquals(maybeResult.get().getName(), "Emma Watson", "Wrong name!");
    }

    @Test
    public void findByIdNegativeTest() {
        var maybeResult = underTest.findById(10000);
        assertTrue(maybeResult.isEmpty());
    }

    @Test(dataProvider = "personFilterDataProvider")
    public void findAllByNameTest(PersonFilterDto personFilterDto, int expectedPersonsAmount) {
        var persons = underTest.findAllByName(personFilterDto);
        assertEquals(persons.size(), expectedPersonsAmount);
    }

    @Test(dependsOnMethods = {"findAllByNameTest"})
    public void saveTest() {
        Person person = Person.builder()
                .name("Test")
                .birthday(LocalDateFormatter.format("1990-03-03"))
                .build();
        underTest.save(person);
        Assert.assertNotNull(person.getId());
    }

    @DataProvider(name = "personFilterDataProvider")
    public static Object[][] personFilterDataProvider() {
        return new Object[][]{
                {buildPersonFilterDto("Emma Watson"), 1},
                {buildPersonFilterDto(""), 0},
                {buildPersonFilterDto(null), 0},
        };
    }

    private static PersonFilterDto buildPersonFilterDto(String name) {
        return PersonFilterDto.builder()
                .name(name)
                .build();
    }
}
