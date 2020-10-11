package com.example.recipely;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiCall {

    public List<String> ingredients;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    public ApiCall(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String call() {
        String ingredients_literal = "{'ingredients':["
                + "'potatoes',"
                + "'apples',"
                + "'honey'"
                + "]}";

        RequestBody body = RequestBody.create(JSON, ingredients_literal);

        String url="https://agile-sands-61557.herokuapp.com/application/recipes/";
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return "";
    }

 /*
    Call post(String url, String json, Callback callback) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
          String bowlingJson(List<String> recipeList) {
        String incredientStr="";
        for(String s:recipeList){
            incredientStr+="\\\""+s+"\\\",";
        }
        incredientStr=incredientStr.substring(0,incredientStr.length()-1);
        return "{\\\"ingredients\\\":["
                + incredientStr
                + "]}";
    }
  }*/


}