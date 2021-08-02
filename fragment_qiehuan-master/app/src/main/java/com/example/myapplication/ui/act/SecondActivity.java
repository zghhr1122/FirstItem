package com.example.myapplication.ui.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.tool.TabReflexUtil;
import com.example.myapplication.ui.frag.ContactFragment;
import com.example.myapplication.ui.frag.HomeFragment;
import com.example.myapplication.ui.frag.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * tablayout切换fragment
 */
public class SecondActivity extends AppCompatActivity {

    private TabLayout tl;
    private ViewPager vp;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        tl = findViewById(R.id.sec_tl);
        vp = findViewById(R.id.sec_vp);
        //设置list装载fragment
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ContactFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
//        vp设置适配器 和fragments关联
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tl.setupWithViewPager(vp);

//        tablayout的数据源
        stringList = new ArrayList<>();
        stringList.add("首页");
        stringList.add("精选");
        stringList.add("我的");
        stringList.add("我的");
        stringList.add("我的");
        for (int i = 0; i < stringList.size(); i++) {
            tl.getTabAt(i).setText(stringList.get(i));
        }
        Log.e("判断","版本"+Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O ){ // 8.0以下版本
            Log.e("8以下","888888888888888");
            TabReflexUtil.reflex(tl);
        }else {   // 8.0以上google修复
            Log.e("大于8","888888888888888");
        }
    }


}
