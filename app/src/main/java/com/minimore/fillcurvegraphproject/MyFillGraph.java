package com.minimore.fillcurvegraphproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static android.R.attr.width;

/**
 * Created by goodn on 2017-10-27.
 */

public class MyFillGraph extends View {

    private int graphWidth;
    private Paint paint;
    private Path path;
    private LinearGradient shader;

    public MyFillGraph(Context context) {
        super(context);
        init();

    }

    public MyFillGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        graphWidth = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }


    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        float radius = 50.0f;
        CornerPathEffect corEffect = new CornerPathEffect(radius);
        paint.setPathEffect(corEffect);

        path = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int oneGraphWidth = graphWidth / 4;
        int startX = getLeft();
        int startY = getBottom();
        int moveX;
        int moveY;
        path.reset();
        int graphValue = 100;

        moveX = startX;
        moveY = startY;
        path.moveTo(moveX, moveY);

        for (int i = 0; i < 4; i++) {
            graphValue = graphValue + 100;
            moveY = moveY - graphValue;
            path.lineTo(moveX, moveY);
            moveX = moveX + oneGraphWidth;
            path.lineTo(moveX, moveY);
        }

        path.lineTo(moveX, getBottom());

//        graphValue = 100;
//        moveY = moveY - graphValue;
//        path.lineTo(moveX, moveY);
//        moveX = moveX + oneGraphWidth;
//        path.lineTo(moveX, moveY);
//
//        graphValue = 200;
//        moveY = moveY - graphValue;
//        path.lineTo(moveX, moveY);
//        moveX = moveX + oneGraphWidth;
//        path.lineTo(moveX, moveY);
//
//        graphValue = 300;
//        moveY = moveY - graphValue;
//        path.lineTo(moveX, moveY);
//        moveX = moveX + oneGraphWidth;
//        path.lineTo(moveX, moveY);
//
//        graphValue = 400;
//        moveY = moveY - graphValue;
//        path.lineTo(moveX, moveY);
//        moveX = moveX + oneGraphWidth;
//        path.lineTo(moveX, moveY);

        paint.setColor(getResources().getColor(R.color.gradientStart, null));
        shader = new LinearGradient(0, 0, 0, getHeight(), R.color.gradientStart, R.color.gradientEnd, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawPath(path, paint);
    }
}
