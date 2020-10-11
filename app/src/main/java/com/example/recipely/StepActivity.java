package com.example.recipely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StepActivity extends AppCompatActivity {

    private Intent intent;
    private ListView listview;
    private StepListViewAdapter adapter = new StepListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);

        intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("recipeInfo");

        TextView recipeNameTxtView = (TextView) findViewById(R.id.recipe_name_txtView);
        recipeNameTxtView.setText(item.getTitle());

        listview = (ListView) findViewById(R.id.step_list_view);
        listview.setAdapter(adapter);

        for(String s:item.getSteps()){
            adapter.addItem(s);
        }
/*
        //스텝받아와서 리스트에 뿌려주기
        adapter.addItem("1. Place chopped tomatoes, sliced cucumber, sliced red onion, diced avocado, and chopped cilantro into a large salad bowl");
        adapter.addItem("2. Place chopped tomatoes, sliced cucumber, sliced red onion, diced avocado, and chopped cilantro into a large salad bowl");
        adapter.addItem("3. Place chopped tomatoes, sliced cucumber, sliced red onion, diced avocado, and chopped cilantro into a large salad bowl");
*/
        adapter.notifyDataSetChanged();

        //뒤로가기버튼
        ImageButton backbtn = (ImageButton) findViewById(R.id.back_btn);
        backbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepActivity.this.finish();
            }
        });

        //홈버튼
        ImageButton homebtn = (ImageButton) findViewById(R.id.home_btn);
        homebtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepActivity.this.finish();
            }
        });

    }

}
