package com.app.pay.cn.pay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PayPasswordLayout mPayLyt;
    private PasswordLayout mPasswordLyt;
    private VirtualKeyboardView mKeyBord;
    private TextView pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    int pos;

    private void initView() {
        mPayLyt = (PayPasswordLayout) findViewById(R.id.pay_lyt);
        mPayLyt.setOnClickListener(this);
        mPasswordLyt = (PasswordLayout) findViewById(R.id.password_lyt);
        mPasswordLyt.setOnClickListener(this);
        mKeyBord = findViewById(R.id.key_bord);
        pd = findViewById(R.id.pd);
        mKeyBord.setKeyboardListener(new VirtualKeyboardView.KeyboardListener() {
            @Override
            public void click(int action, String value) {
                boolean istheFocus;
                if (action == DELETE) {
                    istheFocus = mPasswordLyt.deletePassword();
                    if (!istheFocus) {
                        mPayLyt.deletePassword();
                    }
                } else if (action == INPUT) {
                    istheFocus = mPayLyt.appendPassword(value);
                    if (!istheFocus) {
                        mPasswordLyt.appendPassword(value);
                    }
                } else if (action == HIDE) {

                }
                pd.setText("first:" + mPayLyt.getPassword() + "second:" + mPasswordLyt.getPassword());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.pay_lyt:
                pos = 0;
                break;
            case R.id.password_lyt:
                pos = 1;
                break;
        }
    }

}
