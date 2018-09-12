package com.example.admin.andassistant.usersapi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationFormValidationTest {

    private String email;
    private String password;
    private String repeated;
    private AuthenticationFormValidation validation;

    @Before
    public void setUp() {
        email = "some@email.address";
        password = "123";
        repeated = "123";
    }

    @After
    public void tearDown() {
        validation = null;
    }

    @Test
    public void detectEmptyEmail() {
        email = "";

        validation = new AuthenticationFormValidation(email, password, repeated);
        int validationCode = validation.validationCode();

        assertEquals(AuthenticationFormValidation.EMAIL_EMPTY, validationCode);
    }

    @Test
    public void detectInvalidEmail() {
        email = "aa.a";

        validation = new AuthenticationFormValidation(email, password, repeated);
        int validationCode = validation.validationCode();

        assertEquals(AuthenticationFormValidation.EMAIL_INVALID, validationCode);
    }

    @Test
    public void detectEmptyPassword() {
        password = "";

        validation = new AuthenticationFormValidation(email, password, repeated);
        int validationCode = validation.validationCode();

        assertEquals(AuthenticationFormValidation.PASSWORD_EMPTY, validationCode);
    }

    @Test
    public void detectPasswordMismatch() {
        password = "phrase";
        repeated = "notSamePhrase";

        validation = new AuthenticationFormValidation(email, password, repeated);
        int validationCode = validation.validationCode();

        assertEquals(AuthenticationFormValidation.PASSWORD_MISMATCH, validationCode);
    }

    @Test
    public void detectInvalidPassword() {
        password = '\u0001' + "notAllowed";
        repeated = password;

        validation = new AuthenticationFormValidation(email, password, repeated);
        int validationCode = validation.validationCode();

        assertEquals(AuthenticationFormValidation.PASSWORD_INVALID, validationCode);
    }

}