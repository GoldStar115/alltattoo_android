package com.app.allotattoo.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.allotattoo.R;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        //            /* New Handler to start the Menu-Activity
//         * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
//                    SplashActivity.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
    }
}
