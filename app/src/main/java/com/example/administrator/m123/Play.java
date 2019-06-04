package com.example.administrator.m123;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import tool.SendGet;

import static tool.StringDeal.StringDeal;
public class Play extends  Activity {
    TextView mTextView;
    ImageView mimage,view;
    TextView jTextView;
    String string;
    Button  dowmload_btn, play_btn;
    String str, str2;
    Text text1;
    private Button cancel;
    private Button collect;
    private String showdata;

    // List<String> list;
    static List<String> jianList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        mTextView = (TextView) findViewById(R.id.fanhui123);
        Intent intent = getIntent();
        showdata = intent.getStringExtra("showdata");
        mTextView.setText(showdata);

        collect =(Button) findViewById(R.id.collect);
        cancel =(Button) findViewById(R.id.cancel);

        mimage = (ImageView) findViewById(R.id.movies);
        jTextView = (TextView) findViewById(R.id.datashow);
       // Intent intent = getIntent();
        str = intent.getStringExtra("showdata").trim();
        int i = Integer.parseInt(str);

        play_btn = (Button) findViewById(R.id.player);

        view= (ImageView)findViewById(R.id.back);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play.this,MainActivity.class);
                startActivity(intent);
            }
        });

        CheckVedio check = new CheckVedio(i);
        Thread thread = new Thread(check);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int j = 0; j < jianList.size(); j++) {
            System.out.println(jianList.get(j));
        }
        Glide.with(Play.this).load(jianList.get(3)).into(mimage);
        jTextView.append(jianList.get(5) + "名称:" + jianList.get(0) + "\n");
        jTextView.append("类型:" + jianList.get(6) + "\n");
        jTextView.append("上映时间:" + jianList.get(7) + "\n");
        jTextView.append("简介:" + jianList.get(11));
//       Send send1 = new Send1(TextView.getText().toString());
        jianList.clear();

        collect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
                //点击收藏 跳转收藏页面
                Toast.makeText(Play.this, "收藏成功"+showdata, Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View v) {
                reject();
                Toast.makeText(Play.this, "取消成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void reject() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = SendGet.SendGet("/DeleteServlet/collect", "id=2&vid="+showdata+"");
                    String[] ss = StringDeal(s);
                    for(int i = 0;i < ss.length;i++){
                        string=ss[i];
                    }
                } catch (Exception e) {
                    //txt.append("出错！");
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void send(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = SendGet.SendGet("/InsertServlet/collect", "id=2&vid="+showdata+"");
                    System.out.println(s);
                    String[] ss = StringDeal(s);
                    for(int i = 0;i < ss.length;i++){
                        string=ss[i];
                    }
                } catch (Exception e) {
                    //txt.append("出错！");
                    e.printStackTrace();
                }
            }
        }).start();

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str2 = jianList.get(0);
                System.out.println(str2);
                send();
                Intent intent = new Intent(Play.this, ShowPlay.class);
                intent.putExtra("shoudata", string);
                startActivity(intent);
            }
        });


    }

    }


class CheckVedio implements Runnable{
    private int vid;
    public CheckVedio(int vid){
        this.vid = vid;
    }

    @Override
    public void run() {
        String[] ss = StringDeal(SendGet.SendGet("/QueryServlet/vedio01","vid="+vid));
        for (int i=0;i<ss.length;i++) {
            Play.jianList.add(ss[i]);
        }
    }

}





