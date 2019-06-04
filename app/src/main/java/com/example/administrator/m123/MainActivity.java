package com.example.administrator.m123;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tool.QueryVedThred;
import tool.SendGet;

import static tool.StringDeal.StringDeal;

public class MainActivity extends AppCompatActivity{
    ImageButton myhome,download,homepage,vip;
    TextView txt;
    TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
    String str;
    String string;
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;
    Button recommend;
    private Button btn;
    private ViewPager viewPager;
    private LinearLayout point_group;
    private TextView image_desc;


    //首页轮播效果，先获取图片资源id
    private final int[] images = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5};
    // 图片标题集合
    private final String[] imageDescriptions = {"钢铁侠",
            "复仇者联盟", "琅琊榜", "海贼王", "快乐大本营"};
    private ArrayList<ImageView> imageList;
    // 上一个页面的位置
    protected int lastPosition = 0;
    // 判断是否自动滚动viewPager
    private boolean isRunning = true;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 执行滑动到下一个页面
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                // 在发一个handler延时
                handler.sendEmptyMessageDelayed(0, 1500);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        //绑定imagebutton
        btn1 = (ImageButton) findViewById(R.id.recommend1);
        btn2 = (ImageButton) findViewById(R.id.recommend2);
        btn3 = (ImageButton) findViewById(R.id.recommend3);
        btn4 = (ImageButton) findViewById(R.id.recommend4);
        btn5 = (ImageButton) findViewById(R.id.recommend5);
        btn6 = (ImageButton) findViewById(R.id.recommend6);
        btn7 = (ImageButton) findViewById(R.id.recommend7);
        btn8 = (ImageButton) findViewById(R.id.recommend8);
        btn9 = (ImageButton) findViewById(R.id.recommend9);
        btn10 = (ImageButton) findViewById(R.id.recommend10);

        recommend = (Button) findViewById(R.id.recommend);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //为button绑定事件
        text1 = (TextView) findViewById(R.id.rcmd1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text1);
            }
        });

        text2 = (TextView) findViewById(R.id.rcmd2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text2);
            }
        });
        text3 = (TextView) findViewById(R.id.rcmd3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text3);
            }
        });
        text4 = (TextView) findViewById(R.id.rcmd4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text4);
            }
        });
        text5 = (TextView) findViewById(R.id.rcmd5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text5);
            }
        });
        text6 = (TextView) findViewById(R.id.rcmd6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text6);
            }
        });
        text7 = (TextView) findViewById(R.id.rcmd7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text7);
            }
        });
        text8 = (TextView) findViewById(R.id.rcmd8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text8);
            }
        });
        text9 = (TextView) findViewById(R.id.rcmd9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text9);
            }
        });
        text10 = (TextView) findViewById(R.id.rcmd10);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(text10);
            }
        });
        //轮播效果实现
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        point_group = (LinearLayout) findViewById(R.id.point_group);
        image_desc = (TextView) findViewById(R.id.image_desc);
        image_desc.setText(imageDescriptions[0]);
        // 初始化图片资源
        imageList = new ArrayList<ImageView>();
        for (int i : images) {
            // 初始化图片资源
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(i);
            imageList.add(imageView);
            // 添加指示小点
            ImageView point = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30,
                    30);
            params.rightMargin = 20;
            params.bottomMargin = 10;
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.point_bg);
            if (i == R.drawable.pic1) {
                //默认聚焦在第一张
                point.setBackgroundResource(R.drawable.point_bg_focus);
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            point_group.addView(point);
        }

        viewPager.setAdapter(new MyPageAdapter());
        // 设置当前viewPager的位置
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imageList.size()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 页面切换后调用， position是新的页面位置
                // 实现无限制循环播放
                position %= imageList.size();
                image_desc.setText(imageDescriptions[position]);
                // 把当前点设置为true,将上一个点设为false；并设置point_group图标
                point_group.getChildAt(position).setEnabled(true);
                point_group.getChildAt(position).setBackgroundResource(R.drawable.point_bg_focus);//设置聚焦时的图标样式
                point_group.getChildAt(lastPosition).setEnabled(false);
                point_group.getChildAt(lastPosition).setBackgroundResource(R.drawable.point_bg);//上一张恢复原有图标
                lastPosition = position;
            }
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 页面正在滑动时间回调

            }
            @Override
            public void onPageScrollStateChanged(int state) {
                // 当pageView 状态发生改变的时候，回调
            }
        });
        /*
         * 自动循环： 1.定时器：Timer 2.开子线程：while true循环 3.ClockManger
         * 4.用Handler发送延时信息，实现循环，最简单最方便
         */
        handler.sendEmptyMessageDelayed(0, 1000);

        homepage=(ImageButton)findViewById(R.id.homepage2);
        vip=(ImageButton)findViewById(R.id.vip);
        download=(ImageButton)findViewById(R.id.download);
        myhome=(ImageButton)findViewById(R.id.myhome);

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Myhome.class);
                startActivity(intent);
            }
        });
        vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Myhome.class);
                startActivity(intent);
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Myhome.class);
                startActivity(intent);
            }
        });
        myhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Myhome.class);
                startActivity(intent);
            }
        });
    }

    private void s10(TextView text){
        Thread thread = new Thread(new QueryVedThred(text.getText().toString().trim(),"","",""));
        thread.start();
        try {
            thread.join();
            if(QueryVedThred.result != 0) {
                Intent intent = new Intent(MainActivity.this, Play.class);
                intent.putExtra("showdata", QueryVedThred.list.get(0)+"");
//                QueryVedThred.list.clear();
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this,"传递的数值为空!",Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
// 停止滚动
        isRunning = false;
        super.onDestroy();
    }
    private class MyPageAdapter extends PagerAdapter {
        // 需要实现以下四个方法
        @Override
        public int getCount() {
            // 获得页面的总数
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            // 判断view和Object对应是否有关联关系
            if (view == object) {
                return true;
            } else {
                return false;
            }
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 获得相应位置上的view； container view的容器，其实就是viewpage自身,
            // position: viewpager上的位置
            // 给container添加内容
            container.addView(imageList.get(position % imageList.size()));
            return imageList.get(position % imageList.size());
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 销毁对应位置上的Object
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
            object = null;
        }
    }
    private void send(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = SendGet.SendGet("/QueryServlet/vedio01", "vid=1");
                    txt.setText(s);
                    String[] ss = StringDeal(s);
                    for(int i = 0;i < ss.length;i++){
                        System.out.println(ss[i]);
                        txt.append(ss[i]);
                    }
                } catch (Exception e) {
                    //txt.append("出错！");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
