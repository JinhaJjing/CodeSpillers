package com.example.recipely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {

    private ListView listview;
    private RecipeListViewAdapter adapter = new RecipeListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        listview = (ListView) findViewById(R.id.recipe_list_view);
        listview.setAdapter(adapter);

        //가능한 레시피 가져와서 뿌려주기
        adapter.addItem("TOMATO SOUP");
        adapter.addItem("TOMATO SOUP");
        adapter.addItem("TOMATO SOUP");
        adapter.notifyDataSetChanged();

        ImageButton backbtn = (ImageButton) findViewById(R.id.back_btn);
        backbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeActivity.this, MainActivity.class);
                RecipeActivity.this.finish();
                startActivity(intent);
            }
        });


    }

}
