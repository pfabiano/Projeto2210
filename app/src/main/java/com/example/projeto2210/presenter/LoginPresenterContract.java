package com.example.projeto2210.presenter;


import android.app.Activity;

import com.example.projeto2210.model.User;


public class LoginPresenterContract {
    public interface view {
        public void message(String msg);
        public Activity getActivity();
        public void preferencesUserUpdate(int userId);
    }

    public interface presenter {
        public void checkLogin(String login, String password);
        //public void validLogin(User user);

        void validLogin(User user);
    }
}