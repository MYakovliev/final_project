package com.epam.web.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;

import static org.testng.Assert.*;

public class LotValidatorTest {

    @DataProvider(name = "validName")
    public Object[] createValidNames(){
        return new Object[]{"name", "Actually valid name.", "This: 'also' name (valid)", "Just \"name\" = -34"};
    }

    @DataProvider(name = "invalidName")
    public Object[] createInvalidNames(){
        return new Object[]{"<!-- non !-->", "<c:if/>", "1234567890123456782345678912345678921345678912345", "3/4*5/12"};
    }

    @DataProvider(name = "validTime")
    public Object[][] createValidTimes(){
        return new Object[][]{{Timestamp.valueOf("2022-10-11 12:00:00"),Timestamp.valueOf("2022-11-12 14:00:00")},
                {Timestamp.valueOf("2022-09-01 14:00:00"), Timestamp.valueOf("2024-01-09 14:00:00")},
                {null, Timestamp.valueOf("2022-01-03 14:00:00")}};
    }

    @DataProvider(name = "invalidTime")
    public Object[][] createInvalidTimes(){
        return new Object[][]{{Timestamp.valueOf("2020-01-03 22:01:01"), Timestamp.valueOf("2022-01-01 12:00:00")},
                {Timestamp.valueOf("2022-03-04 15:00:00"), Timestamp.valueOf("2019-01-02 12:00:00")}};
    }

    @Test(dataProvider = "validName")
    public void testIsValidName(String name) {
        boolean actual = LotValidator.isValidName(name);
        assertTrue(actual);
    }

    @Test(dataProvider = "invalidName")
    public void testIsInvalidName(String name) {
        boolean actual = LotValidator.isValidName(name);
        assertFalse(actual);
    }

    @Test
    public void testIsValidDescription() {
        String description = "Just typical description. Can contain anything to describe product";
        boolean actual = LotValidator.isValidDescription(description);
        assertTrue(actual);
    }

    @Test(dataProvider = "validTime")
    public void testIsValidTime(Timestamp startTime, Timestamp finishTime) {
        boolean actual= LotValidator.isValidTime(startTime, finishTime);
        assertTrue(actual);
    }

    @Test(dataProvider = "invalidTime")
    public void testIsInvalidTime(Timestamp startTime, Timestamp finishTime) {
        boolean actual= LotValidator.isValidTime(startTime, finishTime);
        assertFalse(actual);
    }
}