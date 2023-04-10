package com.example.balls;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbBasketball;
    private CheckBox cbFootball;
    private CheckBox cbBaseball;
    private TextView tvMessage;

    private RadioGroup rgBalls;
    private TextView tvRgMessage;

    private Spinner spFavBall;
    private TextView tvSpMessage;
//    private String[] favBalls = {"籃球", "足球", "棒球"};

    private ImageView ivPokemon;
    private Button btnNext;
    private Button btnPrev;

    private int[] images = {R.drawable.pokemon01, R.drawable.pokemon02, R.drawable.pokemon03};
    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPokemon = findViewById(R.id.iv_pokemon);
        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_next:
                        imageIndex = (imageIndex + 1) % images.length;
                        ivPokemon.setImageResource(images[imageIndex]);
                        break;
                    case R.id.btn_prev:
                        imageIndex--;
                        if (imageIndex < 0) imageIndex += images.length;
                        ivPokemon.setImageResource(images[imageIndex]);
                        break;
                }

            }
        };

        btnNext.setOnClickListener(btnListener);
        btnPrev.setOnClickListener(btnListener);

        createSpinner();
        createRadioGroup();
        createCheckBox();
    }

    private void createSpinner() {
        spFavBall = findViewById(R.id.sp_favball);
        tvSpMessage = findViewById(R.id.tv_sp_message);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, favBalls);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fav_balls, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFavBall.setAdapter(adapter);
        AdapterView.OnItemSelectedListener spListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String msg = "球類中最喜歡";
                msg += adapterView.getSelectedItem().toString();
                tvSpMessage.setText(msg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        spFavBall.setOnItemSelectedListener(spListener);
    }

    private void createRadioGroup() {
        rgBalls = findViewById(R.id.rg_balls);
        tvRgMessage = findViewById(R.id.tv_rb_message);

        RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                String message = "三項球類中，最喜歡第";
                switch (id) {
                    case R.id.rb_basketball:
                        message += "1項 籃球";
                        break;
                    case R.id.rb_football:
                        message += "2項 足球";
                        break;
                    case R.id.rb_baseball:
                        message += "3項 棒球";
                        break;
                }
                tvRgMessage.setText(message);
            }
        };

        rgBalls.setOnCheckedChangeListener(rgListener);
    }

    private void createCheckBox() {
        cbBasketball = findViewById(R.id.cb_basketball);
        cbFootball = findViewById(R.id.cb_football);
        cbBaseball = findViewById(R.id.cb_baseball);
        tvMessage = findViewById(R.id.tv_message);

        CompoundButton.OnCheckedChangeListener cbListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String message = "最喜歡的球類有：";
                int count = 0;

                if (cbBasketball.isChecked()) {
                    message += "籃球";
                    count++;
                }

                if (cbFootball.isChecked()) {
                    message += "足球";
                    count++;
                }

                if (cbBaseball.isChecked()) {
                    message += "棒球";
                    count++;
                }

                tvMessage.setText(message + " 共" + count + "項");
            }
        };

        cbBasketball.setOnCheckedChangeListener(cbListener);
        cbFootball.setOnCheckedChangeListener(cbListener);
        cbBaseball.setOnCheckedChangeListener(cbListener);
    }
}