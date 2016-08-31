package com.chebao.quickIndexbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 快速索引
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView currentWord;
    private QuickIndexBar quickIndexBar;
    private ListView listview;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.listview);
        currentWord = (TextView)findViewById(R.id.currentWord);


        //1. 准备数据
        fillList();

        //2. 对数据进行排序
        Collections.sort(friends);

        //2. 设置adapter
        adapter = new MyAdapter(friends,this);
        listview.setAdapter(adapter);

        //快速索引工具条
        quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
        quickIndexBar.setOnTouchLetterListener(new QuickIndexBar.OnTouchLetterLintener() {
            @Override
            public void onToucheLetter(String letter) {
                //letter是接口传出来的字母
                //Log.e("tag","letter:"+ letter);
                //根据当前触摸的字母去listview集合中找这个字母，找到后把对应的字母设置到屏幕顶端
                for (int i = 0; i < friends.size() ; i++) {
                    String firstWord = friends.get(i).getPinyin().charAt(0)+"";
                    if (letter.equals(firstWord)){
                        // 相等的话就把当前item放到屏幕顶端
                        listview.setSelection(i);
                        break; // 找到第一个就停止循环
                    }
                }

                //显示当前触摸的字母
                showCurrentWord(letter);
            }
        });

        //测试汉字转拼音
        //Log.e("tag",PinYinUtil.getPinyin("闫万福"));
        //Log.e("tag",PinYinUtil.getPinyin("*闫#万福"));

        // ViewHelper 是第三方库动画库 NineOldAndroids的子类
        // 通过缩小currentWord来实现隐藏
        ViewHelper.setScaleX(currentWord,0);
        ViewHelper.setScaleY(currentWord,0);
    }

    /**
     * 显示当前触摸的字母
     * @param letter
     */
    private boolean isScale = false;
    private Handler handler = new Handler();
    private void showCurrentWord(String letter) {
        // 使用动画替代VISIBLE
        //currentWord.setVisibility(View.VISIBLE);
        currentWord.setText(letter);
        if (!isScale){
            isScale = true;
            ViewPropertyAnimator.animate(currentWord).scaleX(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(350).start();
            ViewPropertyAnimator.animate(currentWord).scaleY(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(350).start();
        }

        //先移除之前的任务
        handler.removeCallbacksAndMessages(null);


        //延时1500毫秒隐藏currentWord
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //currentWord.setVisibility(View.INVISIBLE);
                ViewPropertyAnimator.animate(currentWord).scaleX(0f).setDuration(450).start();
                ViewPropertyAnimator.animate(currentWord).scaleY(0f).setDuration(450).start();
                isScale = false;
            }
        },1500);

    }

    private void fillList(){

        friends.add(new Friend("潘玮柏"));
        friends.add(new Friend("康有为"));
        friends.add(new Friend("黄蓉"));
        friends.add(new Friend("李宗盛"));
        friends.add(new Friend("那英"));
        friends.add(new Friend("周杰伦"));
        friends.add(new Friend("马云"));
        friends.add(new Friend("闫万福"));
        friends.add(new Friend("阿福"));
        friends.add(new Friend("宗峰岩"));
        friends.add(new Friend("叶德娴"));
        friends.add(new Friend("叶子楣"));
        friends.add(new Friend("富尔特文格勒"));
        friends.add(new Friend("萨顶顶"));
        friends.add(new Friend("任轶男"));
        friends.add(new Friend("陈美"));
        friends.add(new Friend("巴克豪斯"));
        friends.add(new Friend("李伟"));
        friends.add(new Friend("丹弟"));
        friends.add(new Friend("彭雪"));
        friends.add(new Friend("宁浩"));
        friends.add(new Friend("倪睿思"));
        friends.add(new Friend("慕容晓晓"));
        friends.add(new Friend("阿朵"));
        friends.add(new Friend("经蓓"));
        friends.add(new Friend("慕林杉"));
        friends.add(new Friend("北京男孩乐队"));
        friends.add(new Friend("巩俐"));
        friends.add(new Friend("纵贯线"));
        friends.add(new Friend("巩新亮"));
        friends.add(new Friend("傅聪"));
        friends.add(new Friend("巩汉林"));
        friends.add(new Friend("傅海静"));
        friends.add(new Friend("车永莉"));
        friends.add(new Friend("耳光乐队"));
        friends.add(new Friend("二手玫瑰乐队"));

    }
}
