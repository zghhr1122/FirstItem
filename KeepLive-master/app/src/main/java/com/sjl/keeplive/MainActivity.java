package com.sjl.keeplive;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sjl.keeplive.slideImg.ImageAuthenticationView;

public class MainActivity extends AppCompatActivity {

    //滑块
    private SeekBar mSeekBar;
    //自定义的控件
    private ImageAuthenticationView mDY;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mDY = findViewById(R.id.dy_v);
        mSeekBar = findViewById(R.id.sb_dy);
        btn = findViewById(R.id.btn);
    }

    private void initListener() {
        //滑块监听
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //设置滑块移动距离
                mDY.setUnitMoveDistance(mDY.getAverageDistance(seekBar.getMax()) * i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //验证是否拼接成功
                mDY.testPuzzle();
            }
        });

        //控件监听
        mDY.setPuzzleListener(new ImageAuthenticationView.onPuzzleListener() {
            @Override
            public void onSuccess() {
                //mSeekBar.setEnabled(false);//禁止滑动
                Toast.makeText(MainActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail() {
                Toast.makeText(MainActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                mSeekBar.setProgress(0);
                mDY.reSet();
            }
        });

        //重置
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mSeekBar.setEnabled(true);
                mSeekBar.setProgress(0);
                mDY.reSet();
            }
        });
    }
}
