package com.example.administrator.m123;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tool.QueryVedDetailThred;
import tool.QueryVedThred;
import tool.SendGet;

import static tool.StringDeal.StringDeal;
public class Shaixuancartoon extends Activity {
    ImageView bttn1b,bttn2b,bttn3b,bttn4b;
    TextView screenTextView1b,screenTextView2b,screenTextView3b,screenTextView4b;
    Button btn_screen1,btn_screen2,btn_screen3,btn_screen4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaixuancartoon);
        bttn1b = (ImageView)findViewById(R.id.cartoon1);
        bttn2b = (ImageView)findViewById(R.id.cartoon2);
        bttn3b = (ImageView)findViewById(R.id.cartoon3);
        bttn4b = (ImageView)findViewById(R.id.cartoon4);
        screenTextView1b = (TextView)findViewById(R.id.screencartoon1);
        screenTextView2b = (TextView)findViewById(R.id.screencartoon2);
        screenTextView3b = (TextView)findViewById(R.id.screencartoon3);
        screenTextView4b = (TextView)findViewById(R.id.screencartoon4);
        btn_screen1 = (Button) findViewById(R.id.btn_cartoon1);
        btn_screen2 = (Button) findViewById(R.id.btn_cartoon2);
        btn_screen3 = (Button) findViewById(R.id.btn_cartoon3);
        btn_screen4 = (Button) findViewById(R.id.btn_cartoon4);
        btn_screen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                QueryVedThred send = new QueryVedThred("","动漫","",btn_screen1.getText().toString().trim());
                Thread thread = new Thread(send);
                thread.start();
                try {
                    thread.join();
                    QueryVedDetailThred send1 = new QueryVedDetailThred(QueryVedThred.list);
                    Thread thread1 = new Thread(send1);
                    thread1.start();
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Glide.with(Shaixuancartoon.this).load(QueryVedDetailThred.map.get(0).get(3)).into(bttn1b);
                screenTextView1b.setText(QueryVedDetailThred.map.get(0).get(0));
            }
        });
        btn_screen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                QueryVedThred send = new QueryVedThred("","动漫","",btn_screen2.getText().toString().trim());
                Thread thread = new Thread(send);
                thread.start();
                try {
                    thread.join();
                    QueryVedDetailThred send1 = new QueryVedDetailThred(QueryVedThred.list);
                    Thread thread1 = new Thread(send1);
                    thread1.start();
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Glide.with(Shaixuancartoon.this).load(QueryVedDetailThred.map.get(0).get(3)).into(bttn1b);
                screenTextView1b.setText(QueryVedDetailThred.map.get(0).get(0));
            }
        });
        btn_screen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                QueryVedThred send = new QueryVedThred("","动漫","",btn_screen3.getText().toString().trim());
                Thread thread = new Thread(send);
                thread.start();
                try {
                    thread.join();
                    QueryVedDetailThred send1 = new QueryVedDetailThred(QueryVedThred.list);
                    Thread thread1 = new Thread(send1);
                    thread1.start();
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Glide.with(Shaixuancartoon.this).load(QueryVedDetailThred.map.get(0).get(3)).into(bttn1b);
                screenTextView1b.setText(QueryVedDetailThred.map.get(0).get(0));
            }
        });
        btn_screen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                QueryVedThred send = new QueryVedThred("","动漫","",btn_screen4.getText().toString().trim());
                Thread thread = new Thread(send);
                thread.start();
                try {
                    thread.join();
                    QueryVedDetailThred send1 = new QueryVedDetailThred(QueryVedThred.list);
                    Thread thread1 = new Thread(send1);
                    thread1.start();
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Glide.with(Shaixuancartoon.this).load(QueryVedDetailThred.map.get(0).get(3)).into(bttn1b);
                screenTextView1b.setText(QueryVedDetailThred.map.get(0).get(0));
            }
        });
        bttn1b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10( screenTextView1b);
            }
        });
        bttn2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10( screenTextView2b);
            }
        });
        bttn3b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10( screenTextView3b);
            }
        });
        bttn4b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10( screenTextView4b);
            }
        });
    }
    private void clear(){
        screenTextView1b.setText("");
        screenTextView2b.setText("");
        screenTextView3b.setText("");
        screenTextView4b.setText("");
        bttn1b.setImageDrawable(null);
        bttn2b.setImageDrawable(null);
        bttn3b.setImageDrawable(null);
        bttn4b.setImageDrawable(null);
    }
    private void s10(TextView text){
        Thread thread = new Thread(new QueryVedThred(text.getText().toString().trim(),"","",""));
        thread.start();
        try {
            thread.join();
            if(QueryVedThred.result != 0) {
                Intent intent = new Intent(Shaixuancartoon.this, Play.class);
                intent.putExtra("showdata", QueryVedThred.list.get(0)+"");
                startActivity(intent);
            }else {
                Toast.makeText(Shaixuancartoon.this,"传递的数值为空!",Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
