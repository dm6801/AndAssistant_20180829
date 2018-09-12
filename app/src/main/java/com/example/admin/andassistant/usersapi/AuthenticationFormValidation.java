package com.example.admin.andassistant.usersapi;

import java.util.regex.Pattern;

public class AuthenticationFormValidation {
    public static final String TAG = "LoginValidation";

    public static final String REGEX_PASSWORD_PATTERN = "^[!-~]+$";

    public static final String REGEX_EMAIL_ADDRESS =
            "^[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+$";

    public static final int VALID = 0;
    public static final int EMAIL_EMPTY = 1;
    public static final int PASSWORD_EMPTY = 2;
    public static final int EMAIL_INVALID = 3;
    public static final int PASSWORD_MISMATCH = 4;
    public static final int PASSWORD_INVALID = 5;

    private final String email;
    private final String password;
    private final String repeatedPassword;

    public AuthenticationFormValidation(String email, String password, String repeatedPassword) {
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

    public int validationCode() {
        if (email.isEmpty()) {
            return EMAIL_EMPTY;
        }

        if (password.isEmpty()) {
            return PASSWORD_EMPTY;
        }

        if (!isEmailValid()) {
            return EMAIL_INVALID;
        }

        if (!password.equals(repeatedPassword)) {
            return PASSWORD_MISMATCH;
        }

        if (!isPasswordValid()) {
            return PASSWORD_INVALID;
        }

        return VALID;
    }

    private boolean isEmailValid() {
        return Pattern.matches(REGEX_EMAIL_ADDRESS, email);
    }

    private boolean isPasswordValid() {
        return Pattern.matches(REGEX_PASSWORD_PATTERN, password);
    }

}
