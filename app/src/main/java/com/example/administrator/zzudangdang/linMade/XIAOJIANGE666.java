package com.example.administrator.zzudangdang.singlebook;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zzudangdang.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by 沐莲心 on 2017/12/13.
 */

public class dd01 extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> lists = new ArrayList<View>();
    private pagerAdapter_lj pagerAdapter_lj;
    private Bitmap cursor_lj;
    private int offSet;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private int currentItem;
    private int bmpW;
    private ImageView imageView;
    private Animation animation;
    private Matrix matrix = new Matrix();
    private Button cart;
    private Button addcart;
    private ImageView iv;
    private RelativeLayout relativeLayout_lj;
    private PathMeasure mPathMeasure;
    /**
     * 贝塞尔曲线中间过程的点的坐标
     */
    private float[] mCurrentPosition = new float[2];
    private TextView count;
    private int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shangpinye_dd01);

        textView1 = (TextView) findViewById(R.id.thing);
        textView2 = (TextView) findViewById(R.id.introduce);
        textView3 = (TextView) findViewById(R.id.comments);
        textView4 = (TextView) findViewById(R.id.recommends);
        lists.add(getLayoutInflater().inflate(R.layout.page1_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.page2_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.page3_lj, null));
        lists.add(getLayoutInflater().inflate(R.layout.page4_lj, null));
        pagerAdapter_lj = new pagerAdapter_lj(lists);
        viewPager = (ViewPager) findViewById(R.id.viewpager_lj);
        viewPager.setAdapter(pagerAdapter_lj);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int one = offSet * 2 + bmpW;
            int two = one * 2;
            int three = one * 3;

            @Override
            public void onPageSelected(int arg0) {
                Animation animation = null;
                switch (arg0) {
                    case 0:
                        if (currentItem == 1) {
                            animation = new TranslateAnimation(one, 0, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(two, 0, 0, 0);
                        } else if (currentItem == 3) {
                            animation = new TranslateAnimation(three, 0, 0, 0);
                        }
                        break;
                    case 1:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, one, 0, 0);
                        } else if (currentItem == 2) {
                            animation = new TranslateAnimation(two, one, 0, 0);
                        } else if (currentItem == 3) {
                            animation = new TranslateAnimation(three, one, 0, 0);
                        }
                        break;
                    case 2:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, two, 0, 0);
                        } else if (currentItem == 1) {
                            animation = new TranslateAnimation(one, two, 0, 0);
                        } else if (currentItem == 3) {
                            animation = new TranslateAnimation(three, two, 0, 0);
                        }
                    case 3:
                        if (currentItem == 0) {
                            animation = new TranslateAnimation(0, three, 0, 0);
                        }
                        else if (currentItem == 1) {
                            animation = new TranslateAnimation(one, three, 0, 0);
                        }
                        else if (currentItem == 2) {
                            animation = new TranslateAnimation(two, three, 0, 0);
                        }
                }


                currentItem = arg0;
                animation.setDuration(300);
                animation.setFillAfter(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }


            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(2);
            }

        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(3);
            }

        });

        cart = (Button) findViewById(R.id.cart_lj);
        addcart = (Button) findViewById(R.id.addcart_lj);
        relativeLayout_lj = (RelativeLayout) findViewById(R.id.relativeLayout_lj);
        count = (TextView) findViewById(R.id.count_lj);
        final ImageView iv = (ImageView) findViewById(R.id.iv);;
        Button addcart = (Button) findViewById(R.id.addcart_lj);
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCart(iv);
            }
        });

    }


    /**
     * ★★★★★把商品添加到购物车的动画效果★★★★★
     * @param iv
     */
    private void addCart( ImageView iv) {
//      一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        final ImageView goods = new ImageView(dd01.this);
        goods.setImageDrawable(iv.getDrawable());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        relativeLayout_lj.addView(goods, params);

//        二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        relativeLayout_lj.getLocationInWindow(parentLocation);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        addcart.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        cart.getLocationInWindow(endLoc);


//        三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
        float startX = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLocation[0] + cart.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

//        四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, 2*startY/3, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        mPathMeasure = new PathMeasure(path, false);

        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
//      五、 开始执行动画
        valueAnimator.start();

//      六、动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {
                // 购物车的数量加1
                i++;
                count.setText(String.valueOf(i));
                // 把移动的图片imageview从父布局里移除
                relativeLayout_lj.removeView(goods);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }



}