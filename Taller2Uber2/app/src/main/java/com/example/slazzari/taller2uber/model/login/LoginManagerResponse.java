package com.example.slazzari.taller2uber.model.login;

import com.example.slazzari.taller2uber.model.User;

/**
 * Created by slazzari on 11/29/17.
 */

public interface LoginManagerResponse {
    void onLoginWithUser(User user);
    void onLoginWithFailure();
}
