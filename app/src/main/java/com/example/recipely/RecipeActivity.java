package com.example.recipely;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        adapter.addItem("POTATO SOUP");
        adapter.addItem("BEEF SALAD");

        adapter.notifyDataSetChanged();

        Log.d("NAME",listview.toString());

        //레시피 선택 리스너
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                /*BistroListViewItem item = (BistroListViewItem) parent.getItemAtPosition(position);
                Store store = new Store();
                store.setOwnerId(storeList.get(position).getOwnerId());
                store.setId(storeList.get(position).getId());
                store.setName(storeList.get(position).getName());
                store.setLocation(storeList.get(position).getLocation());
                store.setDescription(storeList.get(position).getDescription());
                store.setPhone(storeList.get(position).getPhone());
                //store.setPhotoUri(storeList.get(position).getPhotoUri());*/
                Log.d("NAME","efefe");
                String recipeName=(String)parent.getItemAtPosition(position);

                Log.d("NAME",recipeName);
                Intent intent = new Intent(RecipeActivity.this, StepActivity.class);
                intent.putExtra("recipeInfo", recipeName);
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
