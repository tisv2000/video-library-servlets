package com.github.tisv2000.video_library.dao;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PersonRoleDaoTest {

    private final PersonRoleDao underTest = PersonRoleDao.getInstance();

    @Test
    public void findAllTest() {
        var personRoles = underTest.findAll();
        assertTrue(personRoles.size() > 1);
    }
}
