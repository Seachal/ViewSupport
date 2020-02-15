package com.gcssloop.test.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * *
 * * Path之基本操作
 * Project_Name:ViewSupport
 *
 * @author zhangxc
 * @date 2020-02-13 15:20
 * *
 */
public class TestPathView extends  GridView {
    Paint mPaint = new Paint();

    public TestPathView(Context context) {
        super(context);
    }

    public TestPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
               // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10

        addArcORarcTo(canvas);
    }

    private  void  testLineTo(Canvas canvas){
        //        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
        Path path = new Path();                     // 创建Path
        path.lineTo(200, 200);                      // lineTo
        path.lineTo(200,0);
        canvas.drawPath(path, mPaint);              // 绘制Path
    }

    private  void  testMoveTo(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心

        Path path = new Path();                     // 创建Path

        path.lineTo(200, 200);                      // lineTo

        path.moveTo(200,100);                       // moveTo

        path.lineTo(200,0);                         // lineTo

        canvas.drawPath(path, mPaint);              // 绘制Path
    }

    private  void  testMoveToAndClose(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心

        Path path = new Path();                     // 创建Path

        path.lineTo(200, 200);                      // lineTo

        path.moveTo(200,100);                       // moveTo

        path.lineTo(200,0);                         // lineTo

        path.close();
        canvas.drawPath(path, mPaint);              // 绘制Path
    }

    private  void testPathShap(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        Path src = new Path();

        path.addRect(-200,-200,200,200, Path.Direction.CW);

        src.addCircle(0,0,100, Path.Direction.CW);

//        把 原型 path 添加到矩形 path 中，  同时translate
        path.addPath(src,0,-200);

        mPaint.setColor(Color.BLACK);           // 绘制合并后的路径
        canvas.drawPath(path,mPaint);
    }

    private  void  addArcORarcTo(Canvas canvas){
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        path.lineTo(100,100);

        RectF oval = new RectF(0,0,300,300);

//        path.addArc(oval,0,270);
        // path.arcTo(oval,0,270,true);             // <-- 和上面一句作用等价


        path.arcTo(oval,0,270);
        // path.arcTo(oval,0,270,false);             // <-- 和上面一句作用等价

        canvas.drawPath(path,mPaint);
    }
}
