package com.example.abhinavchinta.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView welcome = (TextView)findViewById(R.id.welcomemessage);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //String usernamee = intent.getStringExtra("username");

        String message = name+ ", welcome to your login page";

        welcome.setText(message);


    }
}
