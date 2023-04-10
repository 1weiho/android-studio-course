package fcu.app.myintentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnBrowse;
    private Button btnDial;
    private Button btnCall;
    private Button btnDetail;
    private EditText etName;
    private static final int REQUEST_CODE_FOR_CALL_ACTION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnBrowse = findViewById(R.id.btn_browse);
        this.btnDial = findViewById(R.id.btn_dial);
        this.btnCall = findViewById(R.id.btn_call);
        this.btnDetail = findViewById(R.id.btn_detail);
        this.etName = findViewById(R.id.et_name);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.btn_browse) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://iecs.fcu.edu.tw"));
                    startActivity(intent);
                } else if (view.getId() == R.id.btn_dial) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0912345678"));
                    startActivity(intent);
                } else if (view.getId() == R.id.btn_call) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_FOR_CALL_ACTION);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0912345678"));
                        startActivity(intent);
                    }
                } else if (view.getId() == R.id.btn_detail) {
                    String name = etName.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("FoodName", name);

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        };

        btnBrowse.setOnClickListener(listener);
        btnDial.setOnClickListener(listener);
        btnCall.setOnClickListener(listener);
        btnDetail.setOnClickListener(listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_FOR_CALL_ACTION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0912345678"));
                startActivity(intent);
            } else {
                Toast.makeText(this, "User does not allow to use Call Action", Toast.LENGTH_LONG).show();
            }

        }
    }
}