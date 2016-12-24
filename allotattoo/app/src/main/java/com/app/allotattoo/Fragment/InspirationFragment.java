package com.app.allotattoo.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.app.allotattoo.Adapter.InspirationAdapter;
import com.app.allotattoo.Model.TattooModel;
import com.app.allotattoo.R;
import com.app.allotattoo.Utils.ParseClass;
import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

/**
 * Created by SecondIrina on 9/10/2016.
 */
public class InspirationFragment extends Fragment implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {
    private String title;
    private int page;
    private StaggeredGridView mGridView;
    private InspirationAdapter inspirationAdapter;
    private ArrayList<TattooModel> tattooModels;
    // newInstance constructor for creating fragment with arguments
    public static InspirationFragment newInstance() {
        Log.d("Fragment Ins1","Fragment Ins");
        InspirationFragment inspirationFragment = new InspirationFragment();
        return inspirationFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Fragment Ins2","Fragment Ins");
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Fragment Ins3","Fragment Ins");
        View view = inflater.inflate(R.layout.fragment_inspiration, container, false);
        mGridView = (StaggeredGridView)view.findViewById(R.id.grid_view);

        if (savedInstanceState == null) {
            final LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        }

        if (inspirationAdapter == null) {
            inspirationAdapter = new InspirationAdapter(getContext(),new ArrayList<TattooModel>());
        }

        mGridView.setAdapter(inspirationAdapter);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
        new GetTattooModels().execute(null,null);
        return view;
    }
//    @Override
//    public void onActivityCreated(final Bundle savedInstanceState) {
//        Log.d("Fragment Ins4","Fragment Ins");
//        super.onActivityCreated(savedInstanceState);
//
//    }
    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
//        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
//        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
//                " visibleItemCount:" + visibleItemCount +
//                " totalItemCount:" + totalItemCount);
//        // our handling
//        if (!mHasRequestedMore) {
//            int lastInScreen = firstVisibleItem + visibleItemCount;
//            if (lastInScreen >= totalItemCount) {
//                Log.d(TAG, "onScroll lastInScreen - so load more");
//                mHasRequestedMore = true;
//                onLoadMoreItems();
//            }
//        }
    }

    private void onLoadMoreItems() {
//        final ArrayList<String> sampleData = SampleData.generateSampleData();
//        for (String data : sampleData) {
//            mAdapter.add(data);
//        }
//        // stash all the data in our backing store
//        mData.addAll(sampleData);
//        // notify the adapter that we can update now
//        mAdapter.notifyDataSetChanged();
//        mHasRequestedMore = false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
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
            ParseClass parseClass = new ParseClass(getContext());
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

}
