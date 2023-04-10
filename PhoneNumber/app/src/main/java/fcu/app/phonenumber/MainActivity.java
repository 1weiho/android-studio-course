package fcu.app.phonenumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnClear;
    private Button btnOk;
    private Button btnClose;
    private TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnClear = findViewById(R.id.btn_clear);
        btnOk = findViewById(R.id.btn_ok);
        btnClose = findViewById(R.id.btn_close);
        tvPassword = findViewById(R.id.tv_password);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num = tvPassword.getText().toString();

                switch (view.getId()) {
                    case R.id.btn_0:
                        tvPassword.setText(num + "0");
                        break;
                    case R.id.btn_1:
                        tvPassword.setText(num + "1");
                        break;
                    case R.id.btn_2:
                        tvPassword.setText(num + "2");
                        break;
                    case R.id.btn_3:
                        tvPassword.setText(num + "3");
                        break;
                    case R.id.btn_4:
                        tvPassword.setText(num + "4");
                        break;
                    case R.id.btn_5:
                        tvPassword.setText(num + "5");
                        break;
                    case R.id.btn_6:
                        tvPassword.setText(num + "6");
                        break;
                    case R.id.btn_7:
                        tvPassword.setText(num + "7");
                        break;
                    case R.id.btn_8:
                        tvPassword.setText(num + "8");
                        break;
                    case R.id.btn_9:
                        tvPassword.setText(num + "9");
                        break;
                    case R.id.btn_clear:
                        tvPassword.setText("");
                        break;
                    case R.id.btn_ok:
                        String password = tvPassword.getText().toString();
                        String msg = "密碼錯誤，請重新輸入";
                        if (password.equals("123456")) {
                            msg = "密碼正確";
                        }
                        Toast toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
                        tvPassword.setText("");
                        toast.show();
                        break;
                    case R.id.btn_close:
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("確認視窗");
                        alert.setIcon(R.mipmap.ic_launcher);
                        alert.setMessage("確定要結束應用程式嗎？");
                        alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alert.show();
                        break;

                }
            }
        };

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btnClear.setOnClickListener(listener);
        btnOk.setOnClickListener(listener);
        btnClose.setOnClickListener(listener);
    }
}