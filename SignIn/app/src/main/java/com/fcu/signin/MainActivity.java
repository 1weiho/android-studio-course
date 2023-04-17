package com.fcu.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button btnClear;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
    Boolean isSignedIn = sharedPreferences.getBoolean("signed_in", false);
    if (!isSignedIn) {
      Intent intent = new Intent(MainActivity.this, SignInActivity.class);
      startActivity(intent);
    }

    btnClear = findViewById(R.id.btn_clear_pref);
    View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
      }
    };

    btnClear.setOnClickListener(onClickListener);
  }
}