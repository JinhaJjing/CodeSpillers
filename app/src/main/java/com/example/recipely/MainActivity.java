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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener{

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

                OkHttpClient client = new OkHttpClient();

                MediaType JSON = MediaType.get("application/json; charset=utf-8"); //error now

                // todo: perhaps use this library for parsing the JSON response, it's your call
                //     https://square.github.io/okhttp/recipes/#parse-a-json-response-with-moshi-kt-java
                // I can also do this part if you tell me where/how do you want me to save the data

                // todo: look into secret management
                //     https://medium.com/@ericfu/securely-storing-secrets-in-an-android-application-501f030ae5a3
                // you can also leave this to me

                String json = bowlingJson(ingredientList);
                RequestBody body = RequestBody.create(json, JSON);

                Request request = new Request.Builder()
                        .url("http://127.0.0.1:3000/recipes/")
                        .post(body)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("LIST",ingredientList.toString());
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.putExtra("recipeList", (Parcelable) ingredientList);
                startActivity(intent);
            }
        }) ;

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ingredientEditTxt.getWindowToken(), 0);
    }

    String bowlingJson(List<String> recipeList) {
        String incredientStr="";
        for(String s:recipeList){
            incredientStr+="'"+s+"',";
        }
        incredientStr=incredientStr.substring(0,incredientStr.length()-2);
        return "{'ingredients':["
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