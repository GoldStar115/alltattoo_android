package com.app.allotattoo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.allotattoo.R;

/**
 * Created by SecondIrina on 9/13/2016.
 */
public class MesFavorUnloginActivity extends AppCompatActivity {
    RelativeLayout btnBack;
    ImageView btnInstConnect, btnFBConnect;
    @Override
    protected void onCreate(Bundle saveInstance)
    {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_mesfavor_unlogin);

        btnBack = (RelativeLayout)findViewById(R.id.btnmesfavor_unloginback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnInstConnect = (ImageView)findViewById(R.id.btninstagram_unlog);
        btnInstConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnFBConnect = (ImageView)findViewById(R.id.btnfacebook_unlog);
        btnFBConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
