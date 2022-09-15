package com.atechno.TemperatureDetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.function.Predicate;

public class Login extends AppCompatActivity {
  TextView Signup;
  Button BtnLogin;

  EditText LoginUsername, LoginPassword;
  ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Signup = findViewById(R.id.signUp);
        BtnLogin = findViewById(R.id.loginBtn);
        LoginUsername = findViewById(R.id.loginUsername);
        LoginPassword = findViewById(R.id.loginPass);
        progressBar = findViewById(R.id.progressBar2);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  username, password;

                username = String.valueOf(LoginUsername.getText());
                password = String.valueOf(LoginPassword.getText());


                if (!username.equals("") && !password.equals("") ) {


                    //Start ProgressBar first (Set visibility VISIBLE)

                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];

                            field[0] = "username";
                            field[1] = "password";

                            //Creating array for data
                            String[] data = new String[2];

                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("http://192.168.188.186/TemperatureHumidityDetector/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)

                                    if (result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),Home.class);
                                        startActivity(intent);
                                        finish();

                                    }else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}