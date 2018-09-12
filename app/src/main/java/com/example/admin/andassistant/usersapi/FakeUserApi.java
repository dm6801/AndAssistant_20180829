package com.example.admin.andassistant.usersapi;

import java.util.HashMap;

public class FakeUserApi implements UserApi {

    HashMap<String, String> users;
    boolean isLogged;
    String loggedUser;

    public FakeUserApi() {
        users = new HashMap<>();
    }

    @Override
    public void register(String email, String password, Callback callback) {
        if (!isRegistered(email)) {
            users.put(email, password);
            callback.onSuccess();
        } else {
            callback.onFailure();
        }
    }

    @Override
    public boolean isRegistered(String email) {
        return users.containsKey(email);
    }

    @Override
    public void login(String email, String password, Callback callback) {
        String cachedPassword = users.get(email);

        if (cachedPassword != null && cachedPassword.equals(password)) {
            isLogged = true;
            loggedUser = email.split("@")[0];
            callback.onSuccess();
        } else {
            callback.onFailure();
        }
    }

    @Override
    public boolean isLogged(String email) {
        return isLogged;
    }

    @Override
    public String getCurrentUserName() {
        return loggedUser;
    }

}
