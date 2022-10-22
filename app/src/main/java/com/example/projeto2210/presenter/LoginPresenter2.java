package com.example.projeto2210.presenter;

import android.content.Intent;

import com.example.projeto2210.model.User;
import com.example.projeto2210.repository.UserRepository;
import com.example.projeto2210.view.Activity2;


public class LoginPresenter2 implements LoginPresenterContract.presenter{
    private LoginPresenterContract.view view;

    public LoginPresenter2(LoginPresenterContract.view view) {
        this.view = view;
    }
    @Override
    public void checkLogin(String login, String password) {
        UserRepository repo  = UserRepository.getInstance(view.getActivity(), null);
        User u = repo.getUserByUserLogin(login);
        if (u == null || ! u.getPassword().equals(password)) {
            view.message("Usuário ou senha Inválido");
        } else {
            u.setPassword("trocada");
            validLogin(u);
        }
    }
    @Override
    public void validLogin(User user) {
        Intent intent = new Intent(view.getActivity(), Activity2.class);
        //intent.putExtra("userId", user.getId());
        intent.putExtra("userObj", user);
        view.getActivity().startActivity(intent);
    }
}