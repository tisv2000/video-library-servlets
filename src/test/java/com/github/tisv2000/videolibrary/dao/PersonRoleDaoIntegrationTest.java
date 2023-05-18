package com.github.tisv2000.videolibrary.dao;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PersonRoleDaoIntegrationTest {

    private final PersonRoleDao personRoleDao = PersonRoleDao.getInstance();

    @Test
    public void findAllTest() {
        var personRoles = personRoleDao.findAll();
        assertTrue(personRoles.size() > 1);
    }
}
