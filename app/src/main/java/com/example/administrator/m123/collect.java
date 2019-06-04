package com.example.administrator.m123;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tool.SendGet;

import static tool.StringDeal.StringDeal;
public class collect extends Activity {
    Button Clean;
    String string;
    TextView collectT1,collectT2,collectT3,collectT4,collectT5,collectT6;
    ImageView collectV1,collectV2,collectV3,collectV4,collectV5,collectV6;
    ImageView view;
@Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.collectfragment);

    collectT1 = (TextView) findViewById(R.id.collectT1);
    collectT2 = (TextView) findViewById(R.id.collectT2);
    collectT3 = (TextView) findViewById(R.id.collectT3);
    collectT4 = (TextView) findViewById(R.id.collectT4);
    collectT5 = (TextView) findViewById(R.id.collectT5);
    collectT6 = (TextView) findViewById(R.id.collectT6);
    if (Myhome.map.get(0) != null){
        collectV1 = (ImageView)findViewById(R.id.collectV1);
        List<String> list_0 = Myhome.map.get(0);
        Glide.with(collect.this).load(list_0.get(3)).centerCrop().into(collectV1);
        collectT1.setText(list_0.get(0));
    }
    if (Myhome.map.get(1) != null){
        collectV2 = (ImageView)findViewById(R.id.collectV2);
        List<String> list_1 = Myhome.map.get(1);
        Glide.with(collect.this).load(list_1.get(3)).centerCrop().into(collectV2);
        collectT2.setText(list_1.get(0));
    }
    if (Myhome.map.get(2) != null){
        collectV3 = (ImageView)findViewById(R.id.collectV3);
        List<String> list_2 = Myhome.map.get(2);
        Glide.with(collect.this).load(list_2.get(3)).centerCrop().into(collectV3);
        collectT3.setText(list_2.get(0));
    }
    if (Myhome.map.get(3) != null){
        collectV4 = (ImageView)findViewById(R.id.collectV4);
        List<String> list_3 = Myhome.map.get(3);
        Glide.with(collect.this).load(list_3.get(3)).centerCrop().into(collectV4);
        collectT4.setText(list_3.get(0));
    }
    if (Myhome.map.get(4) != null){
        collectV5 = (ImageView)findViewById(R.id.collectV5);
        List<String> list_4 = Myhome.map.get(4);
        Glide.with(collect.this).load(list_4.get(3)).centerCrop().into(collectV5);
        collectT5.setText(list_4.get(0));
    }
    if (Myhome.map.get(5) != null){
        collectV6 = (ImageView)findViewById(R.id.collectV6);
        List<String> list_5 = Myhome.map.get(5);
        Glide.with(collect.this).load(list_5.get(3)).centerCrop().into(collectV6);
        collectT6.setText(list_5.get(0));
    }
    Clean = (Button)findViewById(R.id.CleanAll);
    Clean.setOnClickListener(new OnClick());

    view=(ImageView)findViewById(R.id.back);
    view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(collect.this,Myhome.class);
            startActivity(intent);
        }
    });
    }
    class OnClick implements View.OnClickListener{
        public void  onClick(View view){
            reject();
            setContentView(R.layout.cleancollect);
        }
    }
    private void reject() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = SendGet.SendGet("/DeleteServlet/collectall", "");
                    String[] ss = StringDeal(s);
                    for(int i = 0;i < ss.length;i++){
                        System.out.println(ss[i]);
                        string=","+ss[i];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }}
