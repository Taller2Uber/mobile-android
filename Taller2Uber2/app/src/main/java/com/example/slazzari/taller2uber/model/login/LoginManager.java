package com.example.slazzari.taller2uber.model.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.slazzari.taller2uber.MainApplication;
import com.example.slazzari.taller2uber.activity.LoginActivity;
import com.example.slazzari.taller2uber.activity.Register.RegisterActivity;
import com.example.slazzari.taller2uber.activity.home.driver.DriverHomeActivity;
import com.example.slazzari.taller2uber.activity.home.passenger.PassengerHomeActivity;
import com.example.slazzari.taller2uber.model.CurrentUserCredentials;
import com.example.slazzari.taller2uber.model.TrackingLooper;
import com.example.slazzari.taller2uber.model.User;
import com.example.slazzari.taller2uber.model.chat.ChatManager;
import com.example.slazzari.taller2uber.networking.NetworkingConstants;
import com.example.slazzari.taller2uber.networking.interactor.Userinteractor;
import com.facebook.AccessToken;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.slazzari.taller2uber.networking.NetworkingConstants.AUTHORIZATION_KEY;

/**
 * Created by slazzari on 11/29/17.
 */

/*
* Encargado del login de la aplicacion, incluyendo tanto la de facebook como la nativa
* Todas las formas de login requieren de un LoginResponse que es llamado con el resultado del
* Login, este puede ser fallido o exitoso.
* */
public class LoginManager  {


    /*
    * Forma de login sin parámetros, este es llamado cuando se quiere hacer login con data almacenada
    * en el teléfono. Si se pudo hacer el login, entonces llama al LoginResponse con exito,
    * si fallo, con la falla.
    * */
    public void login(LoginManagerResponse loginResponse) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());

        final String userName = preferences.getString("username",null);
        final String password = preferences.getString("password",null);

        if ((userName != null) && (password != null)) {
            login(userName, password, loginResponse);
        } else {
            if (AccessToken.getCurrentAccessToken() != null) {
                String fbToken = AccessToken.getCurrentAccessToken().getToken();
                login(fbToken, loginResponse);
            } else {
                loginResponse.onLoginWithFailure();
            }
        }
    }

    /*
    * Forma de login con usuario y contraseña.
    * */
    public void login(String userName, String password, final LoginManagerResponse loginResponse) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        login(user,loginResponse);
    }


    /*
    * Forma de login con token de facebook
    * */
    public void login(String fbToken, LoginManagerResponse loginResponse) {
        User user = new User();
        user.setFbToken(fbToken);

        login(user, loginResponse);
    }

    /*
    * Forma de login con un user, no importa los parámetros que tenga (fbtoken o user password),
    * este enviará el request para solicitar loguin con dicho user.
    * */
    public void login(final User user, final LoginManagerResponse loginResponse) {
        Userinteractor.loginUser(user).enqueue(
                new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        User responseUser = response.body();

                        String authorization = response.headers().get(AUTHORIZATION_KEY);
                        NetworkingConstants.authToken = authorization;

                        if (response.code() < 200 || response.code() > 300) {
                            loginResponse.onLoginWithFailure();
                            return;
                        } else {
                            if (responseUser == null) {
                                loginResponse.onLoginWithFailure();
                                return;
                            } else {
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
                                SharedPreferences.Editor editor = preferences.edit();

                                editor.putString("username", user.getUserName());
                                editor.putString("password",user.getPassword());

                                editor.commit();



                                CurrentUserCredentials.getInstance().setId(responseUser.getSsId());
                                CurrentUserCredentials.getInstance().setType(responseUser.getType());
                                CurrentUserCredentials.getInstance().setType(responseUser.getType());

                                TrackingLooper.getInstance().beginTracking();

                                loginResponse.onLoginWithUser(responseUser);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        loginResponse.onLoginWithFailure();
                    }
                }
        );
    }


    /*
    * Maneja el logout de la aplicacion, elimina cache setea los singletones en sus respectivos valores.
    * */
    public void logout() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.getAppContext());
        SharedPreferences.Editor editor = preferences.edit();

        String userName = preferences.getString("username",null);
        String password = preferences.getString("password",null);

        if((userName != null) && (password != null)) {
            editor.remove("username").commit();
            editor.remove("password").commit();
            editor.commit();
        }else{
            com.facebook.login.LoginManager.getInstance().logOut();
        }

        ChatManager.getInstance().clearChat();
        TrackingLooper.getInstance().finishTracking();
        CurrentUserCredentials.getInstance().setId(null);
        CurrentUserCredentials.getInstance().setType(null);
    }
}
