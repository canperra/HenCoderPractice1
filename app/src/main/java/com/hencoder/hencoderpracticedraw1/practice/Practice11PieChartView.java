package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.bean.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;

public class Practice11PieChartView extends View {

    private Paint mPaint;
    private List<Data> datas;
    private float max;
    private float total;
    private RectF rect;
    private float radius;
    float startAngle = 0f; // 开始的角度
    float sweepAngle;      // 扫过的角度
    float lineAngle;       // 当前扇形一般的角度
    float lineStartX = 0f; // 直线开始的X坐标
    float lineStartY = 0f; // 直线开始的Y坐标
    float lineEndX;        // 直线结束的X坐标
    float lineEndY;        // 直线结束的Y坐标
    float centreMoveX;     // 圆心位移的X坐标
    float centreMoveY;     // 圆心位移的Y坐标
    private float defaultMoveValue = 20;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        datas = new ArrayList<>();
        datas.add(new Data("Froyo", new Random().nextInt(20), Color.BLACK));
        datas.add(new Data("GB", new Random().nextInt(20), Color.GREEN));
        datas.add(new Data("ICS", new Random().nextInt(20), Color.GRAY));
        datas.add(new Data("JB", new Random().nextInt(20), Color.WHITE));
        datas.add(new Data("KitKat", new Random().nextInt(20), Color.YELLOW));
        datas.add(new Data("L", new Random().nextInt(20), Color.RED));
        datas.add(new Data("M", new Random().nextInt(20), Color.BLUE));
        for (Data data : datas) {
            max = Math.max(max, data.getValue());
            total += data.getValue();
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(getWidth() / 2, getHeight() / 2) * 0.8f;
        rect = new RectF(-radius, -radius, radius, radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        startAngle = 0;
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
//        int moved = 0;
        for (int i = 0; i < datas.size(); i++) {
            mPaint.setColor(datas.get(i).getColor());
            sweepAngle = datas.get(i).getValue() / total * 360;
            lineAngle = startAngle + sweepAngle / 2;
            if (datas.get(i).getValue() == max) {
                lineStartX = (float) ((radius + defaultMoveValue) * Math.cos(lineAngle / 180 * Math.PI));
                lineStartY = (float) ((radius + defaultMoveValue) * Math.sin(lineAngle / 180 * Math.PI));
                lineEndX = (float) ((radius + defaultMoveValue + defaultMoveValue) * Math.cos(lineAngle / 180 * Math.PI));
                lineEndY = (float) ((radius + defaultMoveValue + defaultMoveValue) * Math.sin(lineAngle / 180 * Math.PI));
                centreMoveX = (float) (defaultMoveValue * Math.cos(lineAngle / 180 * Math.PI));
                centreMoveY = (float) (defaultMoveValue * Math.sin(lineAngle / 180 * Math.PI));
                canvas.translate(centreMoveX, centreMoveY);
                canvas.drawArc(rect, startAngle, sweepAngle, true, mPaint);
                canvas.translate(-centreMoveX, -centreMoveY);
            } else {
                lineStartX = (float) (radius * Math.cos(lineAngle / 180 * Math.PI));
                lineStartY = (float) (radius * Math.sin(lineAngle / 180 * Math.PI));
                lineEndX = (float) ((radius + defaultMoveValue) * Math.cos(lineAngle / 180 * Math.PI));
                lineEndY = (float) ((radius + defaultMoveValue) * Math.sin(lineAngle / 180 * Math.PI));
                canvas.drawArc(rect, startAngle, sweepAngle - 1, true, mPaint);
            }
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(20);
            mPaint.setStrokeWidth(2);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, mPaint);
            if (lineAngle > 90 && lineAngle <= 270) {
                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, mPaint);
                canvas.drawText(datas.get(i).getName(), lineEndX - 50 - 10 - mPaint.measureText(datas.get(i).getName()), lineEndY, mPaint);
            } else {
                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, mPaint);
                canvas.drawText(datas.get(i).getName(), lineEndX + 50 + 10, lineEndY, mPaint);
            }
            startAngle += sweepAngle;
        }
    }
}
