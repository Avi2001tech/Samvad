package com.example.samvad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Screen extends AppCompatActivity {
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*AppName = findViewById(R.id.AppName);*/
        lottie = findViewById(R.id.lottie);


        lottie.animate().setDuration(3500).setStartDelay(20);
        lottie.animate().getInterpolator();
        lottie.playAnimation();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent i = new Intent(Splash_Screen.this,verification_numberPage.class);
                startActivity(i);
                finish();

                /*if(mAuth.getCurrentUser()!=null){
                    Intent i = new Intent(splashScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(splashScreen.this,login_page.class);
                    startActivity(i);
                    finish();
                }*/

            }
        },5000);
    }
}