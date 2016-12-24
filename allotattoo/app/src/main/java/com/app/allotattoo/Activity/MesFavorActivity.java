package com.app.allotattoo.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.allotattoo.Adapter.InspirationAdapter;
import com.app.allotattoo.Model.TattooModel;
import com.app.allotattoo.R;
import com.app.allotattoo.Utils.ParseClass;
import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

/**
 * Created by SecondIrina on 9/13/2016.
 */
public class MesFavorActivity extends AppCompatActivity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener  {
    private StaggeredGridView mMesfavorGridView;
    private InspirationAdapter inspirationAdapter;
    private ArrayList<TattooModel> tattooModels;
    RelativeLayout btnBack;
    @Override
    protected void onCreate(Bundle saveInstance)
    {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_mesfavor);
        btnBack = (RelativeLayout)findViewById(R.id.btnmesfavorback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mMesfavorGridView = (StaggeredGridView)findViewById(R.id.mesfavorgrid_view);
        if (inspirationAdapter == null) {
            inspirationAdapter = new InspirationAdapter(this,new ArrayList<TattooModel>());
        }

        mMesfavorGridView.setAdapter(inspirationAdapter);
        mMesfavorGridView.setOnScrollListener(this);
        mMesfavorGridView.setOnItemClickListener(this);
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
}
