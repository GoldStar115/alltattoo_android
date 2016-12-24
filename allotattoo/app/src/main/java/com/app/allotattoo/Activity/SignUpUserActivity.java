package com.app.allotattoo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.app.allotattoo.R;

/**
 * Created by SecondIrina on 9/10/2016.
 */
public class SignUpUserActivity extends AppCompatActivity {
    Button btnSignUpUser_FB, btnSignUpUser_INS;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        btnSignUpUser_FB = (Button)findViewById(R.id.btn_fb_user_signup);
        btnSignUpUser_FB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(SignUpUserActivity.this, PhotoFeedActivity.class);
                SignUpUserActivity.this.startActivity(mainIntent);
            }
        });
        btnSignUpUser_INS = (Button)findViewById(R.id.btn_ins_user_singup);
        btnSignUpUser_INS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(SignUpUserActivity.this, PhotoFeedActivity.class);
                SignUpUserActivity.this.startActivity(mainIntent);
            }
        });
    }
    @Override
    public  boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == event.KEYCODE_BACK)
        {
            onBackPressed();
        }
        return true;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
