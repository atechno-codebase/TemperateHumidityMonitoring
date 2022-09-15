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
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {
    Button SignUp;

    EditText FullName, Username, Password, Email;

    ProgressBar ProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FullName = findViewById(R.id.fullName);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        Email = findViewById(R.id.email);
        SignUp = findViewById(R.id.btnSignup);
        ProgressBar = findViewById(R.id.progressBar);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname, username, password, email;
                fullname= String.valueOf(FullName.getText());
                username = String.valueOf(Username.getText());
                password = String.valueOf(Password.getText());
                email = String.valueOf(Email.getText());

                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {


                    //Start ProgressBar first (Set visibility VISIBLE)

                    ProgressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("http://192.168.188.186/TemperatureHumidityDetector/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    ProgressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)

                                    if (result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),Login.class);
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