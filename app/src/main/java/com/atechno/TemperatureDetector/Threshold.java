package com.atechno.TemperatureDetector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Threshold extends AppCompatActivity {
 Button Set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold);
        Set = findViewById(R.id.set);
         
        Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Threshold.this, "Values Set", Toast.LENGTH_SHORT).show();
            }
        });
    }
}