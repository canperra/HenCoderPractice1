package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.bean.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Practice10HistogramView extends View {


    private Paint mPaint;
    private List<Data> datas;
    private float max;
    private String title = "直方图";

    public Practice10HistogramView(Context context) {
        this(context, null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        datas = new ArrayList<>();
        datas.add(new Data("Froyo", new Random().nextInt(20), Color.BLACK));
        datas.add(new Data("GB", new Random().nextInt(20), Color.BLACK));
        datas.add(new Data("ICS", new Random().nextInt(20), Color.BLACK));
        datas.add(new Data("JB", new Random().nextInt(20), Color.BLACK));
        datas.add(new Data("KitKat", new Random().nextInt(20), Color.BLACK));
        datas.add(new Data("L", new Random().nextInt(20), Color.BLACK));
        datas.add(new Data("M", new Random().nextInt(20), Color.BLACK));
        for (Data data : datas) {
            max = Math.max(max, data.getValue());
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        mPaint.setTextSize(52);
        mPaint.setColor(Color.WHITE);
        canvas.drawText(title, (canvas.getWidth() - mPaint.measureText(title)) / 2, canvas.getHeight() * 0.9f, mPaint);

        canvas.translate(canvas.getWidth() * 0.1f, canvas.getHeight() * 0.7f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawLine(0, 0, canvas.getWidth() * 0.8f, 0, mPaint);//x
        canvas.drawLine(0, 0, 0, -canvas.getHeight() * 0.6f, mPaint);//y

        mPaint.setTextSize(20);
        mPaint.setStyle(Paint.Style.FILL);
        float itemWidth = canvas.getWidth() * 0.8f / datas.size() * 0.8f;
        float itemSpace = canvas.getWidth() * 0.8f / datas.size() * 0.1f;
        for (int i = 0; i < datas.size(); i++) {
            mPaint.setColor(Color.GREEN);
            canvas.drawRect(i * itemWidth + (2 * (i + 1) - 1) * itemSpace, -datas.get(i).getValue() / max * canvas.getHeight() * 0.6f, (i + 1) * itemWidth + (2 * (i + 1) - 1) * itemSpace, 0, mPaint);
            mPaint.setColor(datas.get(i).getColor());
            canvas.drawText(datas.get(i).getName(), i * itemWidth + (2 * (i + 1) - 1) * itemSpace + ((itemWidth - mPaint.measureText(datas.get(i).getName())) / 2), 20, mPaint);
        }
    }
}
