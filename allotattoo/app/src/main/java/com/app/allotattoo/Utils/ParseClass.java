package com.app.allotattoo.Utils;

import android.content.Context;
import android.util.Log;

import com.app.allotattoo.Model.CategoryModel;
import com.app.allotattoo.Model.TattooModel;
import com.app.allotattoo.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SecondIrina on 9/11/2016.
 */
public class ParseClass{
    Context context;
    public ParseClass(Context context){
        this.context = context;
    }
    public JSONObject getTattooModelJSONObject(){
        JSONObject jsonObject = new JSONObject();
        InputStream is =context.getResources().openRawResource(R.raw.jsonfile);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();
        Log.d("JSON String",jsonString);
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject getCategoryModelJSONObject(){
        JSONObject jsonObject = new JSONObject();
        InputStream is =context.getResources().openRawResource(R.raw.jsoncategory);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();
        Log.d("JSON String",jsonString);
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public ArrayList<TattooModel> getTattooModel(JSONObject jsonObject){
        ArrayList<TattooModel> tattooModels = new ArrayList<>();
        TattooModel tattooModel = new TattooModel();
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()){
            String key = iterator.next();
            try{
                Gson gson = new Gson();
                tattooModel = gson.fromJson(jsonObject.get(key).toString(),TattooModel.class);
                tattooModels.add(tattooModel);
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return tattooModels;
    }

    public ArrayList<CategoryModel> getCategoryModel(JSONObject jsonObject){
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        CategoryModel categoryModel = new CategoryModel();
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()){
            String key = iterator.next();
            try{
                Gson gson = new Gson();
                categoryModel = gson.fromJson(jsonObject.get(key).toString(),CategoryModel.class);
                categoryModels.add(categoryModel);
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return categoryModels;
    }
}
