package com.app.allotattoo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.allotattoo.Model.CategoryModel;
import com.app.allotattoo.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by SecondIrina on 9/11/2016.
 */
public class CategoryAdapter extends BaseAdapter {
    private List<CategoryModel> categoryModels;
    private LayoutInflater inflater;
    private Context mContext ;
    Boolean isLike;
    public CategoryAdapter( Context context,ArrayList<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
        isLike = false;
    }
    public void getCategoryModels(ArrayList<CategoryModel> models){
        this.categoryModels = models;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return categoryModels.size();
    }

    @Override
    public CategoryModel getItem(int position) {
        // TODO Auto-generated method stub
        return categoryModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (position > 0 && position %6 == 0)
        {
            Log.d("Position",String.valueOf(position));
            CategoryMarketPost holderMarketPost = new CategoryMarketPost();
            convertView = inflater.inflate(R.layout.adapter_category_post, parent, false);
            holderMarketPost.btnMarketPost = (ImageView)convertView.findViewById(R.id.btnmarketpost);
            holderMarketPost.btnMarketPostCamera = (ImageView)convertView.findViewById(R.id.imgmarketpost);
            convertView.setTag(holderMarketPost);
            return convertView;
        }else if (position > 0 && position % 12 == 0)
        {
            Log.d("Position",String.valueOf(position));
            CategoryMarketShare holderMarketShare =  new CategoryMarketShare();
            convertView = inflater.inflate(R.layout.adapter_category_marketing, parent, false);
            holderMarketShare.btnMarketShare = (ImageView)convertView.findViewById(R.id.btnmarketshare);
            holderMarketShare.imgMarketShare = (ImageView)convertView.findViewById(R.id.imgmarketshare);
            convertView.setTag(holderMarketShare);
            return convertView;
        }else{
            Log.d("Position",String.valueOf(position));
            CategoryListHolder holderList = new CategoryListHolder();
            convertView = inflater.inflate(R.layout.adapter_category_list, parent, false);
            holderList.btnCategoryLike = (ImageView)convertView.findViewById(R.id.btncategorylike);
            holderList.imgCategoryTattoo = (ImageView)convertView.findViewById(R.id.imgstyletattoo);
            holderList.txtCategoryTitle = (TextView)convertView.findViewById(R.id.txtstyletitle);
            holderList.txtCategoryDes = (TextView)convertView.findViewById(R.id.txtstyledes);
            convertView.setTag(holderList);
            final CategoryModel categoryModel = categoryModels.get(position);
            try {
                holderList.txtCategoryTitle.setText(categoryModel.style_title);
                holderList.txtCategoryDes.setText(categoryModel.style_des);

                Picasso.with(mContext)
                        .load(categoryModel.style_image_url)
                        .placeholder(R.drawable.img_styleplaceholer)
                        .error(R.drawable.img_styleplaceholer)
                        .into(holderList.imgCategoryTattoo);

                holderList.btnCategoryLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isLike)
                        {
                            Log.d("LikeClick",String.valueOf(position));
                        }
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
                Log.e("error:", e.toString());
            }
            return convertView;
        }

    }



    private class CategoryListHolder {
        TextView txtCategoryTitle;
        TextView txtCategoryDes;
        ImageView btnCategoryLike;
        ImageView imgCategoryTattoo;
    }
    private class CategoryMarketPost {
        TextView txtMarketPost;
        ImageView btnMarketPost;
        ImageView btnMarketPostCamera;
    }
    private class CategoryMarketShare {
        TextView txtMarketShare;
        ImageView btnMarketShare;
        ImageView imgMarketShare;
    }

}
