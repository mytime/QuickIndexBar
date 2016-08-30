package com.chebao.quickIndexbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by iwan on 16/8/30.
 * 快速索引工具类
 * <p/>
 * 1.重写三个构造方法
 * 2.定义26字母数组
 * 3.在view上绘制26个字母，首先要初始一个画笔 ，重写onDraw方法
 * 4.获取视图宽高 重写onSizeChanged方法
 * 5.绘制文本的x坐标: width/2
 * 绘制文本的Y坐标: 格子高度的一半 + 文字高度的一半 + position * 格子高度
 * 计算触摸点对应的字母：根据触摸点的y坐标除以cellHeight,得到的值就是字母对应的索引
 * <p/>
 * 6. 获取触摸的字母 重写onTouchEvent()
 */
public class QuickIndexBar extends View {
    //定义26字母数组
    private String[] indexArr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //定义一个画笔
    private Paint paint;
    private int width;

    // 格子高度(每个字母看成一个格子)
    private float cellHeight;

    //重写
    public QuickIndexBar(Context context) {
        super(context);
        init();
    }

    //重写
    public QuickIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //重写
    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG); // 初始化画笔，设置抗锯齿，
        paint.setColor(Color.WHITE); // 画笔颜色
        paint.setTextSize(30); // 设置字号16sp
        paint.setTextAlign(Paint.Align.CENTER); //设置显示文字的起点是文字边框的底边的中心（显示文字总长度的一半）

    }

    /**
     * 初始化view宽度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth(); // 视图宽度
        cellHeight = getMeasuredHeight() * 1f / indexArr.length;// 每个格子的高度（ 等分26份）
    }

    //在view上绘制26个字母，
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        String text = "福";
//        float x = width/2;
//        //绘制文字(绘制内容，x坐标，y坐标,画笔);
//        canvas.drawText(text,x,30,paint);

        for (int i = 0; i < indexArr.length; i++) {

            float x = width / 2;
            float y = cellHeight / 2 + getTextHeight(indexArr[i]) / 2 + i * cellHeight;

            // 点击字母时重绘26个字母，被点击的字母绘制成黑色
            paint.setColor(lastIndex == i?Color.BLACK:Color.WHITE);

            canvas.drawText(indexArr[i], x, y, paint);
        }

    }

    private int lastIndex = -1; // 记录上次触摸字母的索引值

    /**
     * 获取触摸的文字
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:           //按下和移动执行同一个代码
                float y = event.getY();             //触摸点的Y坐标
                int index = (int) (y / cellHeight); //触摸点的Y坐标 除以 格子高度，得到索引值
                if (lastIndex != index) {           //记录值和当前值不相等才打印
                    //Log.e("tag", indexArr[index]);  //显示字母

                    if (index >= 0 && index < indexArr.length) { // 合法的值（精益求精）
                        //回调字母
                        if (listener != null) {
                            listener.onToucheLetter(indexArr[index]);
                        }
                    }

                }
                lastIndex = index;

                break;

            case MotionEvent.ACTION_UP:
                lastIndex = -1;                     //重置

                break;

        }
        //按下和拖动时字母颜色变化
        //引起重绘(在这里回调onDraw()方法)
        invalidate();

        return true;
    }

    // 获取文本的高度
    private int getTextHeight(String text) {
        Rect bounds = new Rect();// 矩形对象
        paint.getTextBounds(text, 0, text.length(), bounds); // 获得文本边界
        return bounds.height(); // 高
    }

    private OnTouchLetterLintener listener;

    public void setOnTouchLetterListener(OnTouchLetterLintener listener) {
        this.listener = listener;
    }

    /**
     * 触摸字母的监听器
     */
    public interface OnTouchLetterLintener {
        void onToucheLetter(String letter); //让这个方法传字母出去
    }

}
