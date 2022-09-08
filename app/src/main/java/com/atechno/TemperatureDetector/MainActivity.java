package com.atechno.TemperatureDetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 3000;

    Animation topAnim, bottomAnim, topAnim2 ;
    ImageView spBag;
    TextView spTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        topAnim2 = AnimationUtils.loadAnimation(this,R.anim.top_anim2);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        spBag = findViewById(R.id.splash_bg);
        spTxt = findViewById(R.id.splash_txt);

        spBag.setAnimation(topAnim);
        spTxt.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}