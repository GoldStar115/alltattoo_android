package com.app.allotattoo.Activity;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.allotattoo.Fragment.CategoryFragment;
import com.app.allotattoo.Fragment.InspirationFragment;
import com.app.allotattoo.Model.SharedModel;
import com.app.allotattoo.R;
import com.astuetz.PagerSlidingTabStrip;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class PhotoFeedActivity extends AppCompatActivity implements View.OnClickListener{
    PagerSlidingTabStrip tabhost;

    private ResideMenu resideMenu;
    SharedModel sharedModel;

    ViewPager pager;
    RelativeLayout btnMenu, btnSearch, btnLike;

    private MyPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle saveBundles)
    {
        super.onCreate(saveBundles);
        setContentView(R.layout.activity_photofeed);
        Log.d("State","Create");
        sharedModel = new SharedModel();
        setUpMenu(null);
        btnMenu = (RelativeLayout)findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

        btnLike = (RelativeLayout)findViewById(R.id.btn_like);
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(PhotoFeedActivity.this, MesFavorActivity.class);
                PhotoFeedActivity.this.startActivity(mainIntent);
            }
        });

        btnSearch = (RelativeLayout)findViewById(R.id.btnsearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tabhost = (PagerSlidingTabStrip)findViewById(R.id.tabhost);
        pager = (ViewPager)findViewById(R.id.photofeed_viewpager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabhost.setViewPager(pager);

        //init Selected Item
        pager.setCurrentItem(sharedModel.photofeedIndex);

        tabhost.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                Toast.makeText(getApplicationContext(), "Tab reselected: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        tabhost.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("State","Resume");
        pager.setCurrentItem(sharedModel.photofeedIndex);
    }
    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return getResources().getString(R.string.ins_tab_title);
                case 1:
                    return getResources().getString(R.string.category_tab_title);
                default:
                    return null;

            }
        }
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return InspirationFragment.newInstance();

                case 1:
                    return CategoryFragment.newInstance();

                default:
                    return null;

            }
        }
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

    public void setUpMenu(String strPhotoURL){
        resideMenu = new ResideMenu(this,strPhotoURL);
        resideMenu.setUse3D(false);

        resideMenu.setBackground(R.color.colorwhite);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.5f);


        resideMenu.menuCategory.setOnClickListener(this);
        resideMenu.menuIns.setOnClickListener(this);
        resideMenu.menuProfile.setOnClickListener(this);
        resideMenu.menuFavoris.setOnClickListener(this);
        resideMenu.menuNofications.setOnClickListener(this);
        resideMenu.menuMessages.setOnClickListener(this);
        resideMenu.menuAbouts.setOnClickListener(this);
        resideMenu.menuLogout.setOnClickListener(this);
        // create menu items;
    }
    @Override
    public void onClick(View view) {
        if (view == resideMenu.menuCategory){
            sharedModel.photofeedIndex = 1;
            pager.setCurrentItem(sharedModel.photofeedIndex);
            Toast.makeText(getApplicationContext(), "Menu is menuCategory!", Toast.LENGTH_SHORT).show();

        }else if (view == resideMenu.menuIns){
            sharedModel.photofeedIndex = 0;
            pager.setCurrentItem(sharedModel.photofeedIndex);
            Toast.makeText(getApplicationContext(), "Menu is menuIns!", Toast.LENGTH_SHORT).show();

        }else if (view == resideMenu.menuProfile){
            Intent mainIntent = new Intent(PhotoFeedActivity.this, UserProfileActivity.class);
            startActivity(mainIntent);

        }else if (view == resideMenu.menuFavoris){
              Intent mainIntent = new Intent(PhotoFeedActivity.this, MesFavorActivity.class);
              startActivity(mainIntent);

        }else if (view == resideMenu.menuNofications){
            Toast.makeText(getApplicationContext(), "Menu is menuNofications!", Toast.LENGTH_SHORT).show();

        }else if (view == resideMenu.menuMessages){
            Toast.makeText(getApplicationContext(), "Menu is menuMessages!", Toast.LENGTH_SHORT).show();

        }else if (view == resideMenu.menuAbouts){
            Toast.makeText(getApplicationContext(), "Menu is menuAbouts!", Toast.LENGTH_SHORT).show();

        }else if (view == resideMenu.menuLogout){
            Toast.makeText(getApplicationContext(), "Menu is menuLogout!", Toast.LENGTH_SHORT).show();

        }
        resideMenu.closeMenu();
    }
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

        }

        @Override
        public void closeMenu() {

        }
    };


}
