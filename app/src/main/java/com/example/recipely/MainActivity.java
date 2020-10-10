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

import com.rapidapi.rapidconnect.RapidApiConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener{

    private RapidApiConnect connect = new RapidApiConnect("Recipely", "a0c4ac63f9msh84bc34f67cf6995p170ce3jsn8500097f0637");
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

                //TODO : API request - ingredientList
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://webknox-recipes.p.rapidapi.com/recipes/findByIngredients?number=5&ingredients=apples%252Cflour%252Csugar") //just try
                        .get()
                        .addHeader("x-rapidapi-host", "webknox-recipes.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "98312f1b1fmsh5495b56424f9e31p1e17bcjsn3569e163cd92") //my application key
                        .build();

                List<Item> recipeList=new ArrayList<>();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        List<Item> body = (List<Item>) response.body();
                        for (int i = 0; i < body.size(); i++) {
                            Item item=new Item();
                            item.setTitle(body.get(i).getTitle());
                            item.setImage(body.get(i).getImage());
                            recipeList.add(item);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("LIST",recipeList.toString());
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.putExtra("recipeList", (Parcelable) recipeList);
                startActivity(intent);
            }
        }) ;

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ingredientEditTxt.getWindowToken(), 0);
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