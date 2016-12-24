package com.app.allotattoo.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.allotattoo.Adapter.InspirationAdapter;
import com.app.allotattoo.Model.SharedModel;
import com.app.allotattoo.Model.TattooModel;
import com.app.allotattoo.R;
import com.app.allotattoo.Utils.ParseClass;
import com.etsy.android.grid.StaggeredGridView;
import com.special.ResideMenu.ResideMenu;

import java.util.ArrayList;

/**
 * Created by SecondIrina on 9/13/2016.
 */
public class UserProfileActivity extends AppCompatActivity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener,View.OnClickListener {
    private StaggeredGridView mUserTattooGridView;
    private InspirationAdapter inspirationAdapter;
    private ArrayList<TattooModel> tattooModels;
    private ResideMenu resideMenu;
    SharedModel sharedModel = new SharedModel();
    RelativeLayout btnUserProfileMenu;
    @Override
    protected void onCreate(Bundle saveInstance)
    {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_user_profile);
        setUpMenu(null);

        mUserTattooGridView = (StaggeredGridView)findViewById(R.id.user_profile_grid_view);

        LayoutInflater layoutInflater = getLayoutInflater();

        View header = layoutInflater.inflate(R.layout.user_profile_header, null);
        btnUserProfileMenu = (RelativeLayout)header.findViewById(R.id.btn_menu_userprofile);
        btnUserProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        mUserTattooGridView.addHeaderView(header);
        if (inspirationAdapter == null) {
            inspirationAdapter = new InspirationAdapter(this,new ArrayList<TattooModel>());
        }
        mUserTattooGridView.setAdapter(inspirationAdapter);
        mUserTattooGridView.setOnScrollListener(this);
        mUserTattooGridView.setOnItemClickListener(this);
        new GetTattooModels().execute(null,null);

    }
    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    private class GetTattooModels extends AsyncTask<String, String, ArrayList<TattooModel>> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<TattooModel> doInBackground(String... args) {
            ArrayList<TattooModel> tattooModelArrayList = new ArrayList<>();
            ParseClass parseClass = new ParseClass(getApplicationContext());
            tattooModelArrayList = parseClass.getTattooModel(parseClass.getTattooModelJSONObject());
            return tattooModelArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<TattooModel> tattooModels) {
            if (tattooModels.size() != 0 && tattooModels != null)
            {
                inspirationAdapter.getTattooModels(tattooModels);
                inspirationAdapter.notifyDataSetChanged();
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
            Intent mainIntent = new Intent(UserProfileActivity.this, PhotoFeedActivity.class);
            startActivity(mainIntent);
            Toast.makeText(getApplicationContext(), "Menu is menuCategory!", Toast.LENGTH_SHORT).show();

        }else if (view == resideMenu.menuIns){
            sharedModel.photofeedIndex = 0;
            Intent mainIntent = new Intent(UserProfileActivity.this, PhotoFeedActivity.class);
            startActivity(mainIntent);
            Toast.makeText(getApplicationContext(), "Menu is menuIns!", Toast.LENGTH_SHORT).show();

        }else if (view == resideMenu.menuProfile){


        }else if (view == resideMenu.menuFavoris){
            Intent mainIntent = new Intent(UserProfileActivity.this, MesFavorActivity.class);
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
