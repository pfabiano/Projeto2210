package com.example.projeto2210.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.projeto2210.R;
//import com.example.projeto2210.databinding.Activity2Binding;
import com.example.projeto2210.databinding.Activity2Binding;
import com.example.projeto2210.model.User;

public class Activity2 extends AppCompatActivity {
    private final String TAG = "Activity2";
    private Activity2Binding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_2);
        // substitui isto por isso
        layout = DataBindingUtil.setContentView(this, R.layout.activity_2);
        //User user1 = UserRepository.getInstance().getUserById(getIntent().getIntExtra("id", -1));
        User user1 = getIntent().getParcelableExtra("userObj");
        layout.setUser(user1);
    }
}