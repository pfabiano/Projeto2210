package com.example.projeto2210.presenter;


import android.content.Intent;

import com.example.projeto2210.model.User;
import com.example.projeto2210.repository.UserRepository;
import com.example.projeto2210.view.MainActivity;


public class LoginPresenter implements LoginPresenterContract.presenter{
    private LoginPresenterContract.view view;

    public LoginPresenter(LoginPresenterContract.view view) {
        this.view = view;
    }
    @Override
    public void checkLogin(String login, String password) {
        UserRepository repo  = UserRepository.getInstance(view.getActivity(), null);
        User u = repo.getUserByUserLogin(login);
        if (u == null || ! u.getPassword().equals(password)) {
            view.message("Usuário ou senha Inválido");
        } else {
            //u.setPassword("trocada");
            validLogin(u);
        }
    }
    @Override
    public void validLogin(User user) {
        Intent intent = new Intent(view.getActivity(), MainActivity.class);
        //intent.putExtra("userId", user.getId());
        intent.putExtra("userObj", user);
        view.preferencesUserUpdate(user.getId());
        view.getActivity().startActivity(intent);
    }
}