package com.app.allotattoo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.allotattoo.R;

/**
 * Created by SecondIrina on 9/13/2016.
 */
public class MesFavorTattooNoActivity extends AppCompatActivity {
    RelativeLayout btnBack;
    @Override
    protected void onCreate(Bundle saveInstance)
    {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_mesfavor_no);
        btnBack = (RelativeLayout)findViewById(R.id.btnmesfavor_noback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
