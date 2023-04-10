package com.example.listviewapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvFoods = findViewById(R.id.lv_foods);

        List<FoodItem> lsFoods = new ArrayList<FoodItem>();
        lsFoods.add(new FoodItem(R.drawable.pokemon_1, "漢堡", 50));
        lsFoods.add(new FoodItem(R.drawable.pokemon_2, "飯糰", 30));
        lsFoods.add(new FoodItem(R.drawable.pokemon_3, "可樂", 20));

        ListViewAdapter adapter = new ListViewAdapter(this, lsFoods);
        lvFoods.setAdapter(adapter);
    }
}