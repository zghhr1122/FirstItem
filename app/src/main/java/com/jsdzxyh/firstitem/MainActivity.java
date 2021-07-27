package com.jsdzxyh.firstitem;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    //ImageView动态数组
    private List<ImageView> mImageList = new ArrayList<ImageView>();
    private MyAdapter mAdapter;
    //图片数组
    private int[] mPics = new int[]{R.drawable.ic_launcher_background, R.drawable.g2, R.drawable.g3};

    private  int  mNum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindID();
        getData();
        initAdapter();
        //第一次显示小白点
        mLinearLayout.getChildAt(0).setEnabled(true);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mLinearLayout.getChildAt(mNum).setEnabled(false);
                mLinearLayout.getChildAt(position).setEnabled(true);
                mNum = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 绑定ID
     */
    private void bindID() {
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mLinearLayout = (LinearLayout) findViewById(R.id.main_linear);
    }
    /**
     * 获取数据
     */
    private void getData() {
        //设置图片
        ImageView imageView;
        View view;
        for (int pic : mPics) {
            imageView = new ImageView(MainActivity.this);
            imageView.setBackgroundResource(pic);
            //添加到数组
            mImageList.add(imageView);

            //创建底部指示器(小圆点)
            view = new View(MainActivity.this);
            view.setBackgroundResource(R.drawable.background);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            //设置间隔
            if (pic != mPics[0]) {
                layoutParams.leftMargin = 10;
            }
            //添加到LinearLayout
            mLinearLayout.addView(view, layoutParams);
        }
    }


    /**
     * 设置适配器
     */
    private void initAdapter() {
        mAdapter = new MyAdapter();
        mViewPager.setAdapter(mAdapter);
    }
    /**
     * 适配器
     */
    private class MyAdapter extends PagerAdapter {

        //大小
        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //获取图片view
            ImageView imageView = mImageList.get(position);
            //设置到容器,也就是ViewPager
            container.addView(imageView);
            //返回控件
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //从容器中删除
            container.removeView(mImageList.get(position));
        }
    }
}