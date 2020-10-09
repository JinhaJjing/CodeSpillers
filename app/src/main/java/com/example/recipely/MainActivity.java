package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private IngredientListViewAdapter adapter = new IngredientListViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.ingredient_list_view);
        listview.setAdapter(adapter);

        Button addbtn = (Button) findViewById(R.id.btn_add);
        addbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ingredientEditTxt = (EditText) findViewById(R.id.editTxt_ingredient);
                String ingredientName = ingredientEditTxt.getText().toString();

                ingredientEditTxt.setText("");

                Log.d("INGREDIENT",ingredientName);

                adapter.addItem(ingredientName); //ingredientQuantity
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void deleteIngredient(View v) {
        TextView deleteTxtView = (TextView) v.findViewById(R.id.btn_delete);
        int position = listview.getPositionForView(v);
        adapter.deleteItem(position);
        adapter.notifyDataSetChanged();
    }

}