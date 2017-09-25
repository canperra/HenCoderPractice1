package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    Paint paint4 = new Paint();

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(Color.BLACK);
        paint1.setAntiAlias(true);
        canvas.drawCircle(200, 200, 80, paint1);

        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(1);
        paint2.setColor(Color.BLACK);
        paint2.setAntiAlias(true);
        canvas.drawCircle(400, 200, 80, paint2);

        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.BLUE);
        paint3.setAntiAlias(true);
        canvas.drawCircle(200, 400, 80, paint3);

        paint4.setStyle(Paint.Style.STROKE);
        paint4.setColor(Color.BLACK);
        paint4.setStrokeWidth(10);
        paint4.setAntiAlias(true);
        canvas.drawCircle(400, 400, 80, paint4);
    }
}
