package com.app.allotattoo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.allotattoo.Model.TattooModel;
import com.app.allotattoo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by SecondIrina on 9/10/2016.
 */
public class InspirationAdapter extends BaseAdapter {

    private List<TattooModel> tattooModels;
    private LayoutInflater inflater;
    private Context mContext ;
    public InspirationAdapter(Context context,ArrayList<TattooModel> tattooModels) {
        this.tattooModels = tattooModels;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }
    public void getTattooModels(ArrayList<TattooModel> tattooModels){
        this.tattooModels = tattooModels;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return tattooModels.size();
    }

    @Override
    public TattooModel getItem(int position) {
        // TODO Auto-generated method stub
        return tattooModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_ins_grid, parent, false);
            holder.imgtattoo = (ImageView)convertView.findViewById(R.id.imgtattoo);
            holder.imgPhoto = (CircleImageView)convertView.findViewById(R.id.imgphoto);
            holder.btnPostLike = (ImageView)convertView.findViewById(R.id.btnpostlike);
            holder.txt_tattooistName = (TextView)convertView.findViewById(R.id.txt_tattooistname);
            holder.view_main = (RelativeLayout)convertView.findViewById(R.id.view_main);
//            final float scale = mContext.getResources().getDisplayMetrics().density;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TattooModel tattooModel = tattooModels.get(position);
        try {
            holder.txt_tattooistName.setText("Ana Marina");
            Picasso.with(mContext)
                    .load(tattooModel.tattoo_image_url)
                    .placeholder(R.drawable.img_placeholder)
                    .error(R.drawable.img_placeholder)
                    .into(holder.imgtattoo);

            Picasso.with(mContext)
                    .load(tattooModel.tattoo_image_url)
                    .placeholder(R.drawable.no_photo)
                    .error(R.drawable.no_photo)
                    .into(holder.imgPhoto);

            holder.btnPostLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Log.e("error:", e.toString());
        }
        final  float scale = 3.1f;
        switch (position%4)
        {
            case 0:
                holder.imgtattoo.getLayoutParams().height = (int) (150 * scale);
                break;
            case 1:
                holder.imgtattoo.getLayoutParams().height = (int) (231 * scale);
                break;
            case 2:
                holder.imgtattoo.getLayoutParams().height = (int) (231 * scale);
                break;
            case 3:
                holder.imgtattoo.getLayoutParams().height = (int) (150 * scale);
                break;
            default:
                break;

        }
        return convertView;
    }
    private class ViewHolder {
            ImageView imgtattoo;
            CircleImageView imgPhoto;
            ImageView btnPostLike;
            RelativeLayout view_main;
            TextView txt_tattooistName;
    }
}
