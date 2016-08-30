package com.chebao.quickIndexbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * Created by iwan on 16/8/30.
 * 快速索引工具类
 *  1.重写三个构造方法
 *  2.定义26字母数组
 *  3.在view上绘制26个字母，首先要初始一个画笔
 *  4.获取视图宽度
 *      绘制文本的x坐标: width/2
 *      绘制文本的Y坐标: 格子高度的一半 + 文字高度的一半 + position * 格子高度
 */
public class QuickIndexBar extends View{
    //定义26字母数组
    private String[] indexArr = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

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
    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG); // 初始化画笔，设置抗锯齿，
        paint.setColor(Color.WHITE); // 画笔颜色
        paint.setTextSize(18); // 设置字号16sp
        paint.setTextAlign(Paint.Align.CENTER); //设置显示文字的起点是文字边框的底边的中心（显示文字总长度的一半）

    }

    /**
     * 初始化view宽度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth(); // 视图宽度
        cellHeight = getMeasuredHeight()*1f/indexArr.length ;// 每个格子的高度（ 等分26份）
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

            float x = width/2;
            float y = cellHeight/2 + getTextHeight(indexArr[i])/2 + i*cellHeight;

            canvas.drawText(indexArr[i],x,y,paint);
        }

    }
    // 获取文本的高度
    private int getTextHeight(String text) {
        Rect bounds = new Rect();// 矩形对象
        paint.getTextBounds(text,0, text.length(),bounds); // 获得文本边界
        return bounds.height(); // 高
    }
}
