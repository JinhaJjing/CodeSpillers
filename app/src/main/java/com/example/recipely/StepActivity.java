package com.example.recipely;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class StepActivity extends AppCompatActivity {

    private ListView listview;
    private IngredientListViewAdapter adapter = new IngredientListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);

        //뒤로가기버튼
        //홈버튼
        //스텝받아와서 리스트에 뿌려주기

    }

}
