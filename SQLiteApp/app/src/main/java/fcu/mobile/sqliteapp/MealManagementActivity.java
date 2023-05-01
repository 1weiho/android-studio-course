package fcu.mobile.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MealManagementActivity extends AppCompatActivity {

  private EditText etMealName;
  private EditText etMealDescription;
  private EditText etMealPrice;
  private Button btnAddMeal;
  private Button btnDelMeal;
  private ListView lvMeals;
  private DatabaseHandler databaseHandler;

  private int toDeleteId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meal_management);

    etMealName = findViewById(R.id.et_meal_name);
    etMealDescription = findViewById(R.id.et_meal_description);
    etMealPrice = findViewById(R.id.et_meal_price);
    btnAddMeal = findViewById(R.id.btn_add_meal);
    btnDelMeal = findViewById(R.id.btn_del_meal);
    lvMeals = findViewById(R.id.lv_all_meals);
    databaseHandler = new DatabaseHandler(this);
    databaseHandler.open();

    View.OnClickListener deleteButtonListener = new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        databaseHandler.deleteMeal(toDeleteId);
        showAllMeals();
      }

    };

    View.OnClickListener listener = new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        String mealName = etMealName.getText().toString();
        String mealDescription = etMealDescription.getText().toString();
        int mealPrice = Integer.parseInt(etMealPrice.getText().toString());
        databaseHandler.addMeal(mealName, mealDescription, mealPrice);
        showAllMeals();
      }

    };

    btnAddMeal.setOnClickListener(listener);
    btnDelMeal.setOnClickListener(deleteButtonListener);


    showAllMeals();

    AdapterView.OnItemClickListener lvItemClickListener = new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectDelete(position);
      }
    };

    lvMeals.setOnItemClickListener(lvItemClickListener);
  }

  private void selectDelete(int position) {
    SimpleCursorAdapter adapter = (SimpleCursorAdapter) lvMeals.getAdapter();
    Cursor cursor = adapter.getCursor();

    if (cursor.moveToPosition(position)) {
      int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
      toDeleteId = id;
    }
  }

  private void showAllMeals() {
    Cursor cursor = databaseHandler.getAllMeals();
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(
      MealManagementActivity.this,
      android.R.layout.simple_list_item_2,
      cursor,
      new String[]{"name", "price"},
      new int[]{android.R.id.text1, android.R.id.text2},
      0
    );
    lvMeals.setAdapter(adapter);
  }
}