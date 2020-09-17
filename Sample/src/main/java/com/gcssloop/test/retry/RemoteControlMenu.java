package com.gcssloop.test.retry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.gcssloop.view.CustomView;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2019-09-14 17:08
 * *
 */
public class RemoteControlMenu extends CustomView {
    //       1.
    //    Region 的区域检测。
    //        2.
    //    Matrix 的坐标映射。
    //


    //
    Path up_path, down_path, left_path, right_path, center_path;
    //  范围
    Region up_region, down_region, left_region, right_region, center_region;

    Matrix mMapMatrix = null;

    //   sca: 标记
    int CENTER = 0;
    int UP = 1;
    int RIGHT = 2;
    int DOWN = 3;
    int LEFT = 4;
    //  标记
    int touchFlag = -1;
    //  手指按下的标记
    int currentFlag = -1;

    MenuListener mListener = null;

    //    默认的磨灰色
    int mDefauColor = 0xFF4E5268;
    //    淡橙色
    int mTouchedColor = 0xFFDF9C81;

    public RemoteControlMenu(Context context) {
        this(context, null);
    }

    public RemoteControlMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        up_path = new Path();
        down_path = new Path();
        left_path = new Path();
        right_path = new Path();
        center_path = new Path();
        up_region = new Region();
        down_region = new Region();
        left_region = new Region();
        right_region = new Region();
        center_region = new Region();

        mDeafultPaint.setColor(mDefauColor);
        mDeafultPaint.setAntiAlias(true);

        mMapMatrix = new Matrix();

    }

    /**
     * sca: measure 后，还是会发生变化，所以需要用 onSizeChanged 再次确定大小。
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMapMatrix.reset();

        // 注意这个区域的大小
        Region globalRegion = new Region(-w, -h, w, h);
        int minWidth = w > h ? h : w;
        minWidth *= 0.8;

        int br = minWidth / 2;
        RectF bigCircle = new RectF(-br, -br, br, br);

        int sr = minWidth / 4;
        RectF smallCircle = new RectF(-sr, -sr, sr, sr);

        float bigSweepAngle = 84;
        float smallSweepAngle = -80;

        // 根据视图大小，初始化 Path 和 Region
        center_path.addCircle(0, 0, 0.2f * minWidth, Path.Direction.CW);
        center_region.setPath(center_path, globalRegion);

        right_path.addArc(bigCircle, -40, bigSweepAngle);
        right_path.arcTo(smallCircle, 40, smallSweepAngle);
        right_path.close();
        right_region.setPath(right_path, globalRegion);

        down_path.addArc(bigCircle, 50, bigSweepAngle);
        down_path.arcTo(smallCircle, 130, smallSweepAngle);
        down_path.close();
        down_region.setPath(down_path, globalRegion);

        left_path.addArc(bigCircle, 140, bigSweepAngle);
        left_path.arcTo(smallCircle, 220, smallSweepAngle);
        left_path.close();
        left_region.setPath(left_path, globalRegion);

        up_path.addArc(bigCircle, 230, bigSweepAngle);
        up_path.arcTo(smallCircle, 310, smallSweepAngle);
        up_path.close();
        up_region.setPath(up_path, globalRegion);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float[] pts = new float[2];
//        pts[0] = event.getRawX();
//        pts[1] = event.getRawY();
        pts[0] = event.getX();
        pts[1] = event.getY();
        mMapMatrix.mapPoints(pts);

        int x = (int) pts[0];
        int y = (int) pts[1];

        switch (event.getActionMasked()) {
            //从日常逻辑上来说，也是，按下+离开，才构成一次点击事件。 down 后，位置会发生位移，那么要确定按压和离开是不是在同一个区域
            case MotionEvent.ACTION_DOWN:
                touchFlag = getTouchedPath(x, y);
                currentFlag = touchFlag;
                break;
            case MotionEvent.ACTION_MOVE:
                currentFlag = getTouchedPath(x, y);
                break;
            case MotionEvent.ACTION_UP:
                currentFlag = getTouchedPath(x, y);
                // 如果手指按下区域和抬起区域相同且不为空，则判断点击事件
                if (currentFlag == touchFlag && currentFlag != -1 && mListener != null) {
                    if (currentFlag == CENTER) {
                        mListener.onCenterCliched();
                    } else if (currentFlag == UP) {
                        mListener.onUpCliched();
                    } else if (currentFlag == RIGHT) {
                        mListener.onRightCliched();
                    } else if (currentFlag == DOWN) {
                        mListener.onDownCliched();
                    } else if (currentFlag == LEFT) {
                        mListener.onLeftCliched();
                    }
                }
                touchFlag = currentFlag = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                touchFlag = currentFlag = -1;
                break;
        }
        // sca: 点击后，请求重新绘制调用 onDraw
        invalidate();
        return true;
    }

    /* // 获取当前触摸点在哪个区域  ,
     *
     * sca：是个重要方法，触摸点是否被某个Region contains
     *
     *
     * */
    int getTouchedPath(int x, int y) {
        if (center_region.contains(x, y)) {
            return 0;
        } else if (up_region.contains(x, y)) {
            return 1;
        } else if (right_region.contains(x, y)) {
            return 2;
        } else if (down_region.contains(x, y)) {
            return 3;
        } else if (left_region.contains(x, y)) {
            return 4;
        }
        return -1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //        sca: 坐标起始点（0,0）平移到此view中心位置
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        // 获取测量矩阵(逆矩阵)  ， 因为画布的坐标不是左上角，移动到了 view 的中心点，所以 Region 坐标计算也要平移？
        if (mMapMatrix.isIdentity()) {
            canvas.getMatrix().invert(mMapMatrix);
        }

        // 绘制默认颜色
        canvas.drawPath(center_path, mDeafultPaint);
        canvas.drawPath(up_path, mDeafultPaint);
        canvas.drawPath(right_path, mDeafultPaint);
        canvas.drawPath(down_path, mDeafultPaint);
        canvas.drawPath(left_path, mDeafultPaint);

        // 绘制触摸区域颜色
        mDeafultPaint.setColor(mTouchedColor);
        if (currentFlag == CENTER) {
            canvas.drawPath(center_path, mDeafultPaint);
        } else if (currentFlag == UP) {
            canvas.drawPath(up_path, mDeafultPaint);
        } else if (currentFlag == RIGHT) {
            canvas.drawPath(right_path, mDeafultPaint);
        } else if (currentFlag == DOWN) {
            canvas.drawPath(down_path, mDeafultPaint);
        } else if (currentFlag == LEFT) {
            canvas.drawPath(left_path, mDeafultPaint);
        }
        //        sca: 恢复默认颜色， mDefauColor，
        mDeafultPaint.setColor(mDefauColor);
    }

    public void setListener(MenuListener listener) {
        mListener = listener;
    }

    // 点击事件监听器
    public interface MenuListener {
        void onCenterCliched();

        void onUpCliched();

        void onRightCliched();

        void onDownCliched();

        void onLeftCliched();
    }

}
