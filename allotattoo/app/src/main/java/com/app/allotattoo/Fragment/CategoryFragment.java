package com.app.allotattoo.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.allotattoo.Activity.CategoryFeedActivity;
import com.app.allotattoo.Activity.MemberTypeActivity;
import com.app.allotattoo.Adapter.CategoryAdapter;
import com.app.allotattoo.Adapter.InspirationAdapter;
import com.app.allotattoo.Model.CategoryModel;
import com.app.allotattoo.Model.TattooModel;
import com.app.allotattoo.R;
import com.app.allotattoo.Utils.ParseClass;
import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

/**
 * Created by SecondIrina on 9/10/2016.
 */
public class CategoryFragment extends Fragment implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener{
    ListView categoryListView;
    CategoryAdapter categoryAdapter;
    // newInstance constructor for creating fragment with arguments
    public static CategoryFragment newInstance() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        categoryListView = (ListView) view.findViewById(R.id.category_list);

        if (savedInstanceState == null) {
            final LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        }

        if (categoryAdapter == null) {
            categoryAdapter = new CategoryAdapter(getContext(),new ArrayList<CategoryModel>());
        }

        categoryListView.setAdapter(categoryAdapter);
        categoryListView.setOnScrollListener(this);
        categoryListView.setOnItemClickListener(this);
        new GetCategoryModel().execute(null,null);
        Log.d("CateFragment Created","Fragment Created");
        return view;
    }

    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
//        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Toast.makeText(getActivity(), "Item Clicked: " + totalItemCount, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
        Intent mainIntent = new Intent(getActivity(), CategoryFeedActivity.class);
        mainIntent.putExtra("Title",categoryAdapter.getItem(position).style_title);
        getActivity().startActivity(mainIntent);
    }

    ///AsyncTask
    private class GetCategoryModel extends AsyncTask<String, String, ArrayList<CategoryModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<CategoryModel> doInBackground(String... args) {
            ArrayList<CategoryModel> categoryModelArrayList = new ArrayList<>();
            ParseClass parseClass = new ParseClass(getContext());
            categoryModelArrayList = parseClass.getCategoryModel(parseClass.getCategoryModelJSONObject());
            return categoryModelArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<CategoryModel> categoryModels) {
            if (categoryModels.size() != 0 && categoryModels != null)
            {

                categoryAdapter.getCategoryModels(categoryModels);
                categoryAdapter.notifyDataSetChanged();
                Log.d("CateFragment Created","GetData");
            }
        }
    }

}
