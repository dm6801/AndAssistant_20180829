package com.example.admin.andassistant.usersapi;

public interface UserApi {
    void register(String email, String password, Callback callback);

    boolean isRegistered(String email);

    void login(String email, String password, Callback callback);

    boolean isLogged(String email);

    interface Callback {
        void onSuccess();

        void onFailure();
    }

    String getCurrentUserName();
}
