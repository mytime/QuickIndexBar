package com.chebao.quickIndexbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 快速索引
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private QuickIndexBar quickIndexBar;
    private ListView listview;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.listview);


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
                Log.e("tag","letter:"+ letter);
            }
        });

        //
        Log.e("tag",PinYinUtil.getPinyin("闫万福"));
        Log.e("tag",PinYinUtil.getPinyin("*闫#万福"));
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
