package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.entity.Person;
import com.github.tisv2000.video_library.util.LocalDateFormatter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class PersonDaoTest {

    private PersonDao personDao = PersonDao.getInstance();


    @Test
    public void testSave() {
        Person person = Person.builder()
                .name("Test")
                .birthday(LocalDateFormatter.format("1990-03-03"))
                .build();
        personDao.save(person);
        Assert.assertNotNull(person.getId());
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testFindById() {
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testFindAllByFilter() {
    }
}
