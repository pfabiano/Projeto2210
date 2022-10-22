package com.example.projeto2210.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.projeto2210.R;
import com.example.projeto2210.model.User;
import com.example.projeto2210.presenter.LoginPresenter;
import com.example.projeto2210.presenter.LoginPresenterContract;
import com.example.projeto2210.repository.OnReadyListener;
import com.example.projeto2210.repository.UserRepository;
import com.example.projeto2210.repository.UserSQLRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginPresenterContract.view {

    private LoginPresenterContract.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getPreferences(0);
        boolean sqlUpdated = preferences.getBoolean("sqlUpdated", false);
        boolean logged = preferences.getBoolean("logged", false);
        int userId = preferences.getInt("userId", -1);

        Log.d("LoginActivity", "Preferences: "+sqlUpdated+", "+logged+ ", "+userId);

        if (userId >=0 && sqlUpdated) {
            User u = UserSQLRepository.getInstance(getActivity()).getUserById(userId);
            if (u != null) {
                ((TextView) findViewById(R.id.edLogin)).setText(u.getUserLogin());
            }
        }


        Log.e("TAG", "onCreate: antes do getInstance" );

        // vou colocar os users vindos do endpoint para dentro do sql após a carga
        UserRepository.getInstance(this, new OnReadyListener() {
                    @Override
                    public void onReady() {
                        if (!sqlUpdated) {
                            List<User> users = UserRepository.getInstance().getUsers();
                            for (User u : users) {
                                UserSQLRepository.getInstance(getActivity()).insertUser(u);
                            }
                            SharedPreferences preferences = getPreferences(0);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("sqlUpdated", true);
                            editor.commit();
                        }
                    }
                }
        );
        Log.e("TAG", "onCreate: depois do getInstance "+UserRepository.getInstance(this, null).getUsers().size());


        //this.presenter = new LoginPresenter(this);
        //trocando a presenter, com o mesmo contrato
        this.presenter = new LoginPresenter(this);

        findViewById(R.id.buttonLogin).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.checkLogin(
                                ((TextView) findViewById(R.id.edLogin)).getText().toString(),
                                ((TextView) findViewById(R.id.edPassword)).getText().toString()
                        );
                    }
                }
        );
    }

    @Override
    public void message(String msg) {
        Snackbar.make(this,findViewById(R.id.edPassword),
                msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void preferencesUserUpdate(int userId) {
        SharedPreferences preferences = getPreferences(0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userId", userId);
        editor.putBoolean("logged", true);
        editor.commit();
    }


}