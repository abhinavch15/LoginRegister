package com.example.abhinavchinta.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {
    public static final String ROOT_URL = "http://192.168.43.165/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText age = (EditText)findViewById(R.id.age);
        final EditText name = (EditText)findViewById(R.id.name);
        final EditText username= (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);



        final Button Register = (Button)findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            private void insertUser(){
                //Here we will handle the http request to insert user to mysql db
                //Creating a RestAdapter
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint(ROOT_URL) //Setting the Root URL
                        .build(); //Finally building the adapter

                //Creating object for our interface
                RegisterAPI api = adapter.create(RegisterAPI.class);

                //Defining the method insertuser of our interface
                api.insertUser(

                        //Passing the values by getting it from editTexts
                        name.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString(),
                        Integer.parseInt(age.getText().toString()),

                        //Creating an anonymous callback
                        new Callback<Response>() {
                            @Override
                            public void success(Response result, Response response) {
                                //On success we will read the server's output using bufferedreader
                                //Creating a bufferedreader object
                                BufferedReader reader = null;
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);

                                //An string to store output from the server
                                String output = "";

                                try {
                                    //Initializing buffered reader
                                    reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                                    //Reading the output in the string
                                    output = reader.readLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                //Displaying the output as a toast
                                Toast.makeText(RegisterActivity.this, output, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                //If any error occured displaying the error as toast
                                Toast.makeText(RegisterActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                );
            }
            @Override
            public void onClick(View v) {
                insertUser();

                //VOLLEY--

                /*
                final String name1 = name.getText().toString();
                final String username1 = username.getText().toString();
                final String password1 = password.getText().toString();
                final int age1 = Integer.parseInt(age.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse= new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");

                            if (success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder BUILDER = new AlertDialog.Builder(RegisterActivity.this);
                                BUILDER.setMessage("Register Failed")
                                        .setNegativeButton("Rety",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(name1,username1,age1,password1,responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);*/




            }
        });






    }



}
