package com.app.pay.cn.pay;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PayPasswordLayout extends RelativeLayout {

    private TextView[] tvList;
    private List<String> password;

    public PayPasswordLayout(Context context) {
        super(context, null);
    }

    public PayPasswordLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tvList = new TextView[6];
        password = new ArrayList<>();
        View view = View.inflate(context, R.layout.weight_pay_password_layout, null);
        tvList[0] = view.findViewById(R.id.tv0);
        tvList[1] = view.findViewById(R.id.tv1);
        tvList[2] = view.findViewById(R.id.tv2);
        tvList[3] = view.findViewById(R.id.tv3);
        tvList[4] = view.findViewById(R.id.tv4);
        tvList[5] = view.findViewById(R.id.tv5);

        addView(view);
        view.requestLayout();
    }

    public boolean appendPassword(String s) {
        if (password.size() < 6) {
            password.add(s);
            int index = password.size() - 1;
            tvList[index].setText("*");
            if (null != listener) {
                listener.inputIndex(index, index == 5);
            }
            return true;
        }
        return false;
    }

    public boolean deletePassword() {
        if (password.size() > 0) {
            int index = password.size() - 1;
            password.remove(index);
            tvList[index].setText("");
            if (null != listener) {
                listener.inputIndex(index, index == 5);
            }
            return true;
        }
        return false;
    }

    PasswordTextListener listener;
    public interface PasswordTextListener{
        void inputIndex(int index, boolean isEnd);
    }

    public void setPasswordTextListener(PasswordTextListener listener) {
        this.listener = listener;
    }

    public String getPassword() {
        StringBuilder sr = new StringBuilder();
        if (password.isEmpty()) {
            return "";
        }
        for (String s: password) {
            sr.append(s);
        }
        return sr.toString();
    }

}
