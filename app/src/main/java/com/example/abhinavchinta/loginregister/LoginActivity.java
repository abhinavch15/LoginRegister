package com.example.abhinavchinta.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username= (EditText)findViewById(R.id.lusername);
        final EditText password = (EditText)findViewById(R.id.lpassword);

        final Button login = (Button)findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username1 = username.getText().toString();
                final String password1 = password.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success= jsonresponse.getBoolean("success");
                            if (success){
                                //String name = jsonresponse.getString("name");
                                int age = jsonresponse.getInt("age");
                                //String username = jsonresponse.getString("username");
                                String name = jsonresponse.getString("name");

                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("name",name);
                                intent.putExtra("username",username1);
                                //intent.putExtra("age",age);

                                startActivity(intent);


                            }else {
                                final AlertDialog.Builder BUILDER = new AlertDialog.Builder(LoginActivity.this);
                                BUILDER.setMessage("Incorrect Credentials")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(username1,password1,responseListener);
                RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        final TextView registerhere = (TextView) findViewById(R.id.newuserreghere);
        registerhere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newuser= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(newuser);
            }
        });
    }
}
