package com.example.lenovo.liweixin201807091602l;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lenovo on 2018/7/9.
 */

//继承view
public class MyView extends View{
    private Path mpath;
    private Paint mpaint;
    private Canvas mcanvas;
    private Bitmap obitmap;

    private Bitmap tBitmap;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(obitmap,0,0,null);
        canvas.drawBitmap(tBitmap,0,0,null);

    }
//初始化
    private void init() {
        mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setAlpha(0);
        mpaint.setStyle(Paint.Style.STROKE);

        mpaint.setStrokeJoin(Paint.Join.ROUND);
        mpaint.setStrokeCap(Paint.Cap.ROUND);
        mpaint.setStrokeWidth(60);
        mpaint.setStrokeJoin(Paint.Join.ROUND);
        mpaint.setStrokeCap(Paint.Cap.ROUND);

        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setStrokeWidth(60);
        mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        mpath=new Path();
        obitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ll);
        tBitmap = Bitmap.createBitmap(obitmap.getWidth(), obitmap.getHeight(), Bitmap.Config.ARGB_8888);//第二章图

        mcanvas=new Canvas(tBitmap);
        mcanvas.drawColor(Color.GRAY);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                mpath.reset();
                mpath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:

                    mpath.lineTo(event.getX(),event.getY());
                break;

            case MotionEvent.ACTION_UP:
                break;
                }
                mcanvas.drawPath(mpath,mpaint);

        invalidate();//重绘
        return true;
        }

}
