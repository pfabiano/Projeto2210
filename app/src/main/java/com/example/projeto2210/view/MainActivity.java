package com.example.projeto2210.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.projeto2210.R;
import com.example.projeto2210.adapter.UsersAddapter;
import com.example.projeto2210.model.User;
import com.example.projeto2210.repository.OnReadyListener;
import com.example.projeto2210.repository.UserRepository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: começando a bagaça");

        //aqui infla o layout xml
        setContentView(R.layout.activity_main);

        /*(R.id.botao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Activity2.class);
                startActivity(intent);
            }
        });
        */
        //findViewById(R.id.botao).setOnClickListener( this );
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Alguém clicou ocl");
                Intent intent = new Intent(view.getContext(), Activity2.class);
                intent.putExtra("userId", 1);


                startActivity(intent);
            }
        };
        //buscando um elemento visual do layout para manuipulação
        Button bt = findViewById(R.id.button02);

        findViewById(R.id.button02).setOnClickListener(ocl);
        findViewById(R.id.botao).setOnClickListener(
                (view) -> {
                    Log.d(TAG, "onClick: Alguém clicou lambda");

                    Intent intent = new Intent(view.getContext(), Activity2.class);
                    startActivity(intent);
                });
        //RecyclerView rc = findViewById(R.id.recycler1);
        //UsersAddapter adapter = new UsersAddapter( UserRepository.getInstance(this).getUsers());
        UserRepository.getInstance(this, new OnReadyListener() {
            @Override
            public void onReady() {
                RecyclerView rc = findViewById(R.id.recycler1);
                UsersAddapter adapter = new UsersAddapter(UserRepository.getInstance().getUsers());
                rc.setAdapter(adapter);
                LinearLayoutManager llm1 = new LinearLayoutManager(getApplicationContext());
                rc.setLayoutManager(llm1);
            }
        });
        //UserRepository.getInstance(this, this);

        //rc.setAdapter(adapter);
        //LinearLayoutManager llm1 = new LinearLayoutManager(this);
        //rc.setLayoutManager(llm1);


        //int id = getIntent().getIntExtra("userId",-1);
        //User user = UserRepository.getInstance().getUserById(id);
        //troquei de envio de id int para objeto
        User user = getIntent().getParcelableExtra("userObj");

        TextView tv = (TextView) findViewById(R.id.editTextTextPersonName2);
        tv.setText("bem vindo " + user.getName() + "(" + user.getPassword() + ")");

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button02) {
            Intent intent = new Intent(view.getContext(), Activity2.class);
            startActivity(intent);
        }
    }
}


