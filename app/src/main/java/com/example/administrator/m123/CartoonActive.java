package com.example.administrator.m123;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import tool.QueryVedThred;
import tool.SendGet;
import static tool.StringDeal.StringDeal;
public class CartoonActive extends AppCompatActivity{
    ImageButton btnn1a,btnn2a,btnn3a,btnn4a;
    Button catton;//先声明哦
    TextView textfczlm,textgtxone,textgtxtwo,textxchh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_cartoon_active);
        btnn1a = (ImageButton) findViewById(R.id.imagefczlma);
        btnn2a = (ImageButton) findViewById(R.id.imagegtxonea);
        btnn3a = (ImageButton) findViewById(R.id.imagegtxtwoa);
        btnn4a = (ImageButton) findViewById(R.id.imagexchha);

        catton = (Button) findViewById(R.id.screencartoon);
        catton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartoonActive.this,Shaixuancartoon.class);
                startActivity(intent);
            }
        });
        textfczlm = (TextView) findViewById(R.id.textfczlma);
        btnn1a.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textfczlm);
            }
        });
        textgtxone = (TextView) findViewById(R.id.textgtxonea);
        //为button绑定事件
        btnn2a.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                s10( textgtxone);
            }
        });
        textgtxtwo = (TextView) findViewById(R.id.textgtxtwoa);
        //为button绑定事件
        btnn3a.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textgtxtwo);
            }
        });
        textxchh=(TextView)findViewById(R.id.textxchha);
        //为button绑定事件
        textxchh = (TextView) findViewById(R.id.textxchha);
        btnn4a.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textxchh);
            }
        });

    }
    private void s10(TextView text){
        Thread thread = new Thread(new QueryVedThred(text.getText().toString().trim(),"","",""));
        thread.start();
        try {
            thread.join();
            if(QueryVedThred.result != 0) {
                Intent intent = new Intent(CartoonActive.this, Play.class);
                intent.putExtra("showdata", QueryVedThred.list.get(0)+"");
                startActivity(intent);
            }else {
                Toast.makeText(CartoonActive.this,"传递的数值为空!",Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




