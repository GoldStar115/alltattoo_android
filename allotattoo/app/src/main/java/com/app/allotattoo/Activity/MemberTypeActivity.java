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
public class MemberTypeActivity extends AppCompatActivity {
    Button btnmembertypeUser, btnmembertypeTattooist;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membertype);
        btnmembertypeUser = (Button)findViewById(R.id.btnmembertype1);
        btnmembertypeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MemberTypeActivity.this, SignUpUserActivity.class);
                MemberTypeActivity.this.startActivity(mainIntent);
//                MemberTypeActivity.this.finish();
            }
        });
        btnmembertypeTattooist = (Button)findViewById(R.id.btnmembertype2);
        btnmembertypeTattooist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MemberTypeActivity.this, SignUpTattooistActivity.class);
                MemberTypeActivity.this.startActivity(mainIntent);
                MemberTypeActivity.this.finish();
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
