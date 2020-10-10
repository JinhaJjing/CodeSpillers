package com.example.recipely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
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
                adapter.addItem(ingredientName); //ingredientQuantity
                adapter.notifyDataSetChanged();
            }
        }

        return false;
    }
}