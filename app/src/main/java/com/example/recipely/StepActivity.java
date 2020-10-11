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
        String item = (String) intent.getSerializableExtra("recipeInfo");

        TextView recipeNameTxtView = (TextView) findViewById(R.id.recipe_name_txtView);
        recipeNameTxtView.setText(item);

        listview = (ListView) findViewById(R.id.step_list_view);
        listview.setAdapter(adapter);

        //for(String s:item.getSteps()){
         //   adapter.addItem(s);
        //}

        //스텝받아와서 리스트에 뿌려주기
        adapter.addItem("1. Place caramels in a 3-quart slow cooker; stir in condensed milk.");
        adapter.addItem("2. Cover and cook on LOW 3 1/2 hours, stirring occasionally, until caramels melt and mixture is smooth.");
        adapter.addItem("3. Serve with apple slices and pound cake squares.");
        adapter.addItem("4. Party Plan: Keep this fondue warm in the slow cooker for easy dipping. Reheat any leftovers in the microwave, stirring at 1-minute intervals until heated through.");

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
                Intent intent = new Intent(StepActivity.this, MainActivity.class);
                StepActivity.this.finish();
                startActivity(intent);
            }
        });

    }

}
