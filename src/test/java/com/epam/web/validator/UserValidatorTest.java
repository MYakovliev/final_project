package com.epam.web.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {
    @DataProvider(name = "validName")
    public Object[] createValidName(){
        return new Object[]{"justName", "Name"};
    }

    @DataProvider(name = "invalidName")
    public Object[] createInvalidName(){
        return new Object[]{"name.com", "invalid name"};
    }

    @DataProvider(name = "validMail")
    public Object[] createValidMail(){
        return new Object[]{"email@rumbler.tv", "my-mail@bntu.by", "mail.my.company@company.by"};
    }

    @DataProvider(name = "invalidMail")
    public Object[] createInvalidMail(){
        return new Object[]{"name.com", "!@mail@invalid.mail", "23${12}@in.valid"};
    }

    @DataProvider(name = "validPassword")
    public Object[] createValidPassword(){
        return new Object[]{"!@#$%^&*()#$%^&",  "passwordTypicalMain"};
    }

    @DataProvider(name = "validBid")
    public Object[] createValidBid(){
        return new Object[]{"14", "12.5", "45.56", "0.03", "12.6"};
    }

    @DataProvider(name = "invalidBid")
    public Object[] createInvalidBid(){
        return new Object[]{"mabid", "-1, 45", "-9.5", "12.556"};
    }

    @DataProvider(name = "invalidPassword")
    public Object[] createInvalidPassword(){
        return new Object[]{"name.co", ""};
    }

    @Test(dataProvider = "validName")
    public void testIsValidName(String name) {
        boolean actual = UserValidator.isValidName(name);
        assertTrue(actual);
    }

    @Test(dataProvider = "invalidName")
    public void testIsInvalidName(String name) {
        boolean actual = UserValidator.isValidName(name);
        assertFalse(actual);
    }

    @Test(dataProvider = "validMail")
    public void testIsValidMail(String mail) {
        boolean actual = UserValidator.isValidMail(mail);
        assertTrue(actual);
    }

    @Test(dataProvider = "invalidMail")
    public void testIsInvalidMail(String mail) {
        boolean actual = UserValidator.isValidMail(mail);
        assertFalse(actual);
    }

    @Test(dataProvider = "validPassword")
    public void testIsValidPassword(String password) {
        boolean actual = UserValidator.isValidPassword(password);
        assertTrue(actual);
    }

    @Test(dataProvider = "invalidPassword")
    public void testIsInvalidPassword(String password) {
        boolean actual = UserValidator.isValidPassword(password);
        assertFalse(actual);
    }

    @Test(dataProvider = "validBid")
    public void testIsValidBid(String bid) {
        boolean actual = UserValidator.isValidBid(bid);
        assertTrue(actual);
    }

    @Test(dataProvider = "invalidBid")
    public void testIsInvalidBid(String bid) {
        boolean actual = UserValidator.isValidBid(bid);
        assertFalse(actual);
    }
}