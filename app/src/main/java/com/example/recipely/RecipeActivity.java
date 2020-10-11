package com.example.recipely;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private Intent intent;
    private ListView listview;
    private RecipeListViewAdapter adapter = new RecipeListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        listview = (ListView) findViewById(R.id.recipe_list_view);
        listview.setAdapter(adapter);

        intent = getIntent();
        RecipeResult recipeResult = (RecipeResult) intent.getSerializableExtra("recipeResultInfo");
        List<Item> recipeList=recipeResult.getRecipeList();
        for(Item i:recipeList){
            adapter.addItem(i);
        }

        if(recipeList.size()==0){
            TextView nothingTxtView = (TextView)findViewById(R.id.nothing_txt_view);
            nothingTxtView.setText("No Result Found");
        }

        //가능한 레시피 가져와서 뿌려주기
        /*adapter.addItem("TOMATO SOUP");
        adapter.addItem("POTATO SOUP");
        adapter.addItem("BEEF SALAD");
*/
        adapter.notifyDataSetChanged();

        Log.d("NAME",listview.toString());

        //레시피 선택 리스너
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Item item=(Item)parent.getItemAtPosition(position);

                Log.d("NAME",item.toString());
                Intent intent = new Intent(RecipeActivity.this, StepActivity.class);
                intent.putExtra("recipeInfo", item);
                startActivity(intent);
            }
        });

        ImageButton backbtn = (ImageButton) findViewById(R.id.back_btn);
        backbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(RecipeActivity.this, MainActivity.class);
                RecipeActivity.this.finish();
                //startActivity(intent);
            }
        });

    }

}
