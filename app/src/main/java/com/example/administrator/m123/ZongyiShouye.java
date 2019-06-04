package com.example.administrator.m123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import tool.QueryVedThred;
import tool.SendGet;

import static tool.StringDeal.StringDeal;

public class ZongyiShouye extends AppCompatActivity {
    ImageButton btnn1, btnn2, btnn3, btnn4, btnn5, btnn6, btnn7, btnn8, btnn9, btnn10;
    Button btn11;//先声明哦
    TextView textfczlm, textgtxone, textgtxtwo, textxchh;
    public static String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.zongyishouye);
        btnn1 = (ImageButton) findViewById(R.id.imagefczlmx);
        btnn2 = (ImageButton) findViewById(R.id.imagegtxonex);
        btnn3 = (ImageButton) findViewById(R.id.imagegtxtwox);
        btnn4 = (ImageButton) findViewById(R.id.imagexchhx);
        btnn5 = (ImageButton) findViewById(R.id.videox5);
        btnn6 = (ImageButton) findViewById(R.id.videox6);
        btnn7 = (ImageButton) findViewById(R.id.videox7);
        btnn8 = (ImageButton) findViewById(R.id.videox8);
        btnn9 = (ImageButton) findViewById(R.id.videox9);
        btnn10 = (ImageButton) findViewById(R.id.videox10);
        btn11 = (Button) findViewById(R.id.chose1);



        textfczlm = (TextView) findViewById(R.id.textfczlmx);
        //为button绑定事件
        btnn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textfczlm);
            }
        });

        textgtxone = (TextView) findViewById(R.id.textgtxonex);
        //为button绑定事件
        btnn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textgtxone);
            }
        });
        textgtxtwo = (TextView) findViewById(R.id.textgtxtwox);
        //为button绑定事件
        btnn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textgtxtwo);
            }
        });
        textxchh = (TextView) findViewById(R.id.textxchhx);
        //为button绑定事件
        btnn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textxchh);
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZongyiShouye.this, ZongyiShaixuan.class);
                startActivity(intent);
            }
        });
    }
    private void s10(TextView text){
        Thread thread = new Thread(new QueryVedThred(text.getText().toString().trim(),"","",""));
        thread.start();
        try {
            thread.join();
            if(QueryVedThred.result!=0) {
                Intent intent = new Intent(ZongyiShouye.this, Play.class);
                intent.putExtra("showdata", QueryVedThred.list.get(0)+"");
                startActivity(intent);
            }else {
                Toast.makeText(ZongyiShouye.this,"传递的数值为空!",Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
