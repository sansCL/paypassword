package com.app.pay.cn.pay;


import android.content.Context;
import android.util.AttributeSet;


public class SquareTextView extends android.support.v7.widget.AppCompatTextView {
    public SquareTextView(Context context) {
        super(context);
    }

    public SquareTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public SquareTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        int childwidthSize = getMeasuredWidth();
        //宽度和高度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childwidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
