package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener{

    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    private EditText ingredientEditTxt;
    private ListView listview;
    private IngredientListViewAdapter adapter = new IngredientListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.ingredient_list_view);
        listview.setAdapter(adapter);

        ingredientEditTxt = (EditText) findViewById(R.id.editTxt_ingredient);
        ingredientEditTxt.setOnEditorActionListener(this);

        ImageButton addbtn = (ImageButton) findViewById(R.id.btn_add);
        addbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredientName = ingredientEditTxt.getText().toString();

                if(ingredientName.equals("")) Toast.makeText(getApplicationContext(), "Enter something.", Toast.LENGTH_SHORT).show();
                else {
                    ingredientEditTxt.setText("");
                    adapter.addItem(ingredientName); //ingredientQuantity
                    adapter.notifyDataSetChanged();
                }
            }
        });

        ImageButton searchbtn = (ImageButton) findViewById(R.id.btn_search) ;
        searchbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> ingredientList=new ArrayList<>();

                for(int i=0;i<adapter.getCount();i++){
                    String ingredientName = (String) adapter.getItem(i);
                    ingredientList.add(ingredientName);
                }
                final List<Item> recipeList=new ArrayList<>();
               /* String json = bowlingJson(ingredientList);

                Log.d("INGREDIENT LIST",json);

                post("https://agile-sands-61557.herokuapp.com/application/recipes/", json, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Something went wrong
                        Log.d("FAIL LIST","fail");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            List<Item> itemList = (List<Item>) response.body();

                            if (itemList != null) {
                                for (int i = 0; i < itemList.size(); i++) {
                                    Item item=new Item();
                                    //item.setImage_url(itemList.get(i).getImage_url());
                                    //item.setTitle(itemList.get(i).getTitle());
                                    //item.setSteps(itemList.get(i).getSteps());

                                    item.setTitle("POTATO SOUP");
                                    List<String> steps=new ArrayList<>();
                                    steps.add("1. aweialdifv");
                                    steps.add("2. alwregjvakjfvv");
                                    steps.add("3. swevearbaer");
                                    item.setSteps(steps);

                                    Log.d("ITEM  LIST",item.toString());
                                    recipeList.add(item);
                                }
                            }

                        } else {
                            int statusCode=response.code();
                            Log.d("FAIL RESPONSE",Integer.toString(statusCode));
                            // Request not successful
                        }
                    }
                });*/

                Log.d("RECIPE LIST",recipeList.toString());
                RecipeResult recipeResult=new RecipeResult();
                recipeResult.setRecipeList(recipeList);
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.putExtra("recipeResultInfo", recipeResult);
                startActivity(intent);
            }
        }) ;

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ingredientEditTxt.getWindowToken(), 0);
    }

    Call post(String url, String json, Callback callback) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

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

    public void deleteIngredient(View v) {
        ImageButton deleteTxtView = (ImageButton) v.findViewById(R.id.btn_delete);
        int position = listview.getPositionForView(v);
        adapter.deleteItem(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onEditorAction(TextView v, int i, KeyEvent keyEvent) {
        if(v.getId()==R.id.editTxt_ingredient && i== EditorInfo.IME_ACTION_DONE){ // 뷰의 id를 식별, 키보드의 완료 키 입력 검출
            String ingredientName = ingredientEditTxt.getText().toString();

            if(ingredientName.equals("")) Toast.makeText(getApplicationContext(), "Enter something.", Toast.LENGTH_SHORT).show();
            else {
                ingredientEditTxt.setText("");
                adapter.addItem(ingredientName);
                adapter.notifyDataSetChanged();
            }
        }

        return false;
    }
}