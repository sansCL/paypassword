package com.app.pay.cn.pay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class VirtualKeyboardView extends RelativeLayout {

    Context context;

    private GridView gridView;

    private ArrayList<String> valueList;

    private RelativeLayout layoutBack;

    public VirtualKeyboardView(Context context) {
        this(context, null);
    }

    public VirtualKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        View view = View.inflate(context, R.layout.weight_virtual_keyboard, null);

        valueList = new ArrayList<>();

        layoutBack = view.findViewById(R.id.layoutBack);

        gridView = view.findViewById(R.id.gv_keybord);

        initValueList();

        setupView();

        addView(view);
    }

    public RelativeLayout getLayoutBack() {
        return layoutBack;
    }

    private void initValueList() {

        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                valueList.add(String.valueOf(i));
            } else if (i == 10) {
                valueList.add("");
            } else if (i == 11) {
                valueList.add(String.valueOf(0));
            } else {
                valueList.add("");
            }
        }
    }

    private void setupView() {

        layoutBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.click(KeyboardListener.HIDE, "");
                }
            }
        });

        KeyBoardAdapter keyBoardAdapter = new KeyBoardAdapter(context, valueList);
        gridView.setAdapter(keyBoardAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != listener) {
                    if (position == 11) {
                        listener.click(KeyboardListener.DELETE, "");
                    } else if (position != 9) {
                        listener.click(KeyboardListener.INPUT, valueList.get(position));
                    }
                }

            }
        });
    }

    KeyboardListener listener;

    public interface KeyboardListener {
        int DELETE = 0;
        int INPUT = 1;
        int HIDE = 2;

        void click(int action, String value);
    }

    public void setKeyboardListener(KeyboardListener listener) {
        this.listener = listener;
    }
}
