package com.app.allotattoo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.app.allotattoo.Adapter.ImagePagerAdapter;
import com.app.allotattoo.R;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SecondIrina on 9/9/2016.
 */
public class OnBoardingActivity extends AppCompatActivity {
    private AutoScrollViewPager viewPager;
    CircleImageView imgBoardDot0, imgBoardDot1, imgBoardDot2;
    private List<Integer> imageIdList;
    Button btnOnBoardNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        btnOnBoardNav = (Button)findViewById(R.id.commencer);
        btnOnBoardNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(OnBoardingActivity.this, MemberTypeActivity.class);
                OnBoardingActivity.this.startActivity(mainIntent);
//                OnBoardingActivity.this.finish();
            }
        });
        imgBoardDot0 = (CircleImageView)findViewById(R.id.board_dot0);
        imgBoardDot1 = (CircleImageView)findViewById(R.id.board_dot1);
        imgBoardDot2 = (CircleImageView)findViewById(R.id.board_dot2);
        viewPager = (AutoScrollViewPager)findViewById(R.id.view_pager);
        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.drawable.onboard1);
        imageIdList.add(R.drawable.onboard2);
        imageIdList.add(R.drawable.onboard3);

        viewPager.setAdapter(new ImagePagerAdapter(getApplicationContext(), imageIdList).setInfiniteLoop(true));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageIdList.size());
    }
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            switch (position%3)
            {
                case 0:
                    imgBoardDot0.setImageResource(R.drawable.dot_white);
                    imgBoardDot1.setImageResource(R.drawable.dot_trans);
                    imgBoardDot2.setImageResource(R.drawable.dot_trans);

                    break;
                case 1:
                    imgBoardDot0.setImageResource(R.drawable.dot_trans);
                    imgBoardDot1.setImageResource(R.drawable.dot_white);
                    imgBoardDot2.setImageResource(R.drawable.dot_trans);

                    break;
                case 2:
                    imgBoardDot0.setImageResource(R.drawable.dot_trans);
                    imgBoardDot1.setImageResource(R.drawable.dot_trans);
                    imgBoardDot2.setImageResource(R.drawable.dot_white);
                   break;
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int arg0) {}
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
