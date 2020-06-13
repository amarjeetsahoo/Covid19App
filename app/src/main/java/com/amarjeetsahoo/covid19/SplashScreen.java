package com.amarjeetsahoo.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=4000;
    Animation atg,btgone,btgtwo;
    ImageView ivSplash;
    TextView namedev,dev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivSplash=findViewById(R.id.ivSplash);
        namedev=findViewById(R.id.namdev);
        dev=findViewById(R.id.dev);

        atg= AnimationUtils.loadAnimation(this,R.anim.atg);
        btgone=AnimationUtils.loadAnimation(this,R.anim.btgone);
        btgtwo=AnimationUtils.loadAnimation(this,R.anim.btgtwo);

        ivSplash.startAnimation(atg);
        dev.startAnimation(btgone);
        namedev.startAnimation(btgtwo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SplashIntent= new Intent(SplashScreen.this,MainActivity.class);
                startActivity(SplashIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}
