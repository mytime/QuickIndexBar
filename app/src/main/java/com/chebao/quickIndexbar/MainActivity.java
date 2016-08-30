package com.chebao.quickIndexbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 快速索引
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private QuickIndexBar quickIndexBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
        quickIndexBar.setOnTouchLetterListener(new QuickIndexBar.OnTouchLetterLintener() {
            @Override
            public void onToucheLetter(String letter) {
                Log.e("tag","letter:"+ letter);
            }
        });
    }
}
