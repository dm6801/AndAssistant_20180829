package com.example.admin.andassistant.loginscreen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.databinding.ActivityLoginBinding;
import com.example.admin.andassistant.home.HomeActivity;
import com.example.admin.andassistant.infrastructure.BaseActivity;
import com.example.admin.andassistant.usersapi.UserApi;
import com.example.admin.andassistant.usersapi.AuthenticationFormValidation;

public class LoginActivity extends BaseActivity {
    public static final String TAG = "LoginActivity";

    public static final int LOGIN_MODE = 1;
    public static final int REGISTER_MODE = 2;

    private int mode = LOGIN_MODE;
    
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deferLoggedInUser();
        bind();
        setMode(LOGIN_MODE);
    }

    private void deferLoggedInUser() {
        if (isUserLoggedIn()) {
            navigateToMain();
        }
    }

    private boolean isUserLoggedIn() {
        String email = isEmailFieldEmpty() ? "" : binding.loginEmail.getText().toString().trim();
        return userApi.isLogged(email);
    }

    private boolean isEmailFieldEmpty() {
        return binding == null || binding.loginEmail.getText().toString().isEmpty();
    }

    private void navigateToMain() {
        startActivity(HomeActivity.newIntent(this));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.loginButtonLogin.setOnClickListener(v -> {
            resetNotification();

            if (mode != LOGIN_MODE) {
                setMode(LOGIN_MODE);
                return;
            }

            validateLoginForm();
        });

        binding.loginButtonRegister.setOnClickListener(v -> {
            resetNotification();

            if (mode != REGISTER_MODE) {
                setMode(REGISTER_MODE);
                return;
            }

            validateRegisterForm();
        });
    }

    private void resetNotification() {
        binding.loginNotification.setText("");
    }

    private void setMode(int mode) {
        this.mode = mode;

        int titleResId = mode == REGISTER_MODE ? R.string.login_activity_register_title : R.string.login_activity_title;
        setTitle(titleResId);

        int repeatPassVisibility = mode == REGISTER_MODE ? View.VISIBLE : View.GONE;
        binding.loginRepeatedPassword.setVisibility(repeatPassVisibility);
    }

    private void validateLoginForm() {
        String email = binding.loginEmail.getText().toString().trim();
        String password = binding.loginPassword.getText().toString().trim();
        int validationCode = validateForm(email, password);

        if (validationCode == AuthenticationFormValidation.VALID) {
            login(email, password);
        }
    }

    private int validateForm(String email, @NonNull String... passwords) {
        String password = passwords[0];
        String repeated = passwords.length == 2 ? passwords[1] : password;

        int code = new AuthenticationFormValidation(email, password, repeated).validationCode();

        switch (code) {
            case AuthenticationFormValidation.EMAIL_EMPTY:
                setNotificationText(R.string.login_exception_email_field_empty);
                break;
            case AuthenticationFormValidation.PASSWORD_EMPTY:
                setNotificationText(R.string.login_exception_password_field_empty);
                break;
            case AuthenticationFormValidation.EMAIL_INVALID:
                setNotificationText(R.string.login_exception_email_invalid);
                break;
            case AuthenticationFormValidation.PASSWORD_MISMATCH:
                setNotificationText(R.string.login_exception_password_mismatch);
                break;
            case AuthenticationFormValidation.PASSWORD_INVALID:
                setNotificationText(R.string.login_exception_password_invalid);
                break;
            case AuthenticationFormValidation.VALID:

                break;
            default:
                setNotificationText(R.string.login_exception_general);
        }

        return code;
    }

    private void setNotificationText(@StringRes int login_exception_email_field_empty) {
        binding.loginNotification.setText(login_exception_email_field_empty);
    }

    private void login(String email, String password) {
        userApi.login(email, password, new UserApi.Callback() {
            @Override
            public void onSuccess() {
                navigateToMain();
            }

            @Override
            public void onFailure() {
                setNotificationText(R.string.login_failure);
                resetPasswordFields();
            }
        });
    }

    private void resetPasswordFields() {
        binding.loginPassword.setText("");
        clearRepeatedPassword();
    }

    private void validateRegisterForm() {
        String email = binding.loginEmail.getText().toString().trim();
        String password = binding.loginPassword.getText().toString().trim();
        String repeated = binding.loginRepeatedPassword.getText().toString().trim();

        int validationCode = validateForm(email, password, repeated);

        if (validationCode == AuthenticationFormValidation.VALID) {
            register(email, password);
        }
    }

    private void register(String email, String password) {
        userApi.register(email, password, new UserApi.Callback() {
            @Override
            public void onSuccess() {
                setNotificationText(R.string.login_register_success);
                setMode(LOGIN_MODE);
                clearRepeatedPassword();
            }

            @Override
            public void onFailure() {
                setNotificationText(R.string.login_register_failure);
                clearRepeatedPassword();
            }
        });
    }

    private void clearRepeatedPassword() {
        binding.loginRepeatedPassword.setText("");
    }

}
