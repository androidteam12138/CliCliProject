package com.example.administrator.m123;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tool.QueryVedDetailThred;
import tool.QueryVedThred;

public class screen extends Activity {
    ImageView bttn1,bttn2,bttn3,bttn4;
    TextView screenTextView1,screenTextView2,screenTextView3,screenTextView4;
    Button btn1,btn2,btn3,btn4;
    static List<String> list = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);
        bttn1 = (ImageView)findViewById(R.id.movie1);
        bttn2 = (ImageView)findViewById(R.id.movie2);
        bttn3 = (ImageView)findViewById(R.id.movie3);
        bttn4 = (ImageView)findViewById(R.id.movie4);
        screenTextView1 = (TextView)findViewById(R.id.screenmovie1);
        screenTextView2 = (TextView)findViewById(R.id.screenmovie2);
        screenTextView3 = (TextView)findViewById(R.id.screenmovie3);
        screenTextView4 = (TextView)findViewById(R.id.screenmovie4);
        btn1 = (Button) findViewById(R.id.Mscreen1);
        btn2 = (Button) findViewById(R.id.Mscreen2);
        btn3 = (Button) findViewById(R.id.Mscreen3);
        btn4 = (Button) findViewById(R.id.Mscreen4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                QueryVedThred send = new QueryVedThred("","电影",btn1.getText().toString().trim(),"");
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
                Glide.with(screen.this).load(QueryVedDetailThred.map.get(0).get(3)).centerCrop().into(bttn1);
                screenTextView1.setText(QueryVedDetailThred.map.get(0).get(0));
                Glide.with(screen.this).load(QueryVedDetailThred.map.get(1).get(3)).centerCrop().into(bttn2);
                screenTextView2.setText(QueryVedDetailThred.map.get(1).get(0));
                Glide.with(screen.this).load(QueryVedDetailThred.map.get(2).get(3)).centerCrop().into(bttn3);
                screenTextView3.setText(QueryVedDetailThred.map.get(2).get(0));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                QueryVedThred send = new QueryVedThred("","电影",btn2.getText().toString().trim(),"");
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

                Glide.with(screen.this).load(QueryVedDetailThred.map.get(0).get(3)).centerCrop().into(bttn1);
                screenTextView1.setText(QueryVedDetailThred.map.get(0).get(0));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                QueryVedThred send = new QueryVedThred("","电影",btn3.getText().toString().trim(),"");
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
                Glide.with(screen.this).load(QueryVedDetailThred.map.get(0).get(3)).centerCrop().into(bttn1);
                screenTextView1.setText(QueryVedDetailThred.map.get(0).get(0));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                QueryVedThred send = new QueryVedThred("","电影",btn4.getText().toString().trim(),"");
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
                Glide.with(screen.this).load(QueryVedDetailThred.map.get(0).get(3)).centerCrop().into(bttn1);
                screenTextView1.setText(QueryVedDetailThred.map.get(0).get(0));

            }
        });



        bttn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(screenTextView1);
            }
        });
        bttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(screenTextView2);
            }
        });
        bttn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(screenTextView3);
            }
        });
    }
    private void clear(){
        screenTextView1.setText("");
        screenTextView2.setText("");
        screenTextView3.setText("");
        screenTextView4.setText("");
        bttn1.setImageDrawable(null);
        bttn2.setImageDrawable(null);
        bttn3.setImageDrawable(null);
        bttn4.setImageDrawable(null);
    }
    private void s10(TextView text){
        Thread thread = new Thread(new QueryVedThred(text.getText().toString().trim(),"","",""));
        thread.start();
        try {
            thread.join();
            if(QueryVedThred.result !=0) {
                Intent intent = new Intent(screen.this, Play.class);
                intent.putExtra("showdata",  QueryVedThred.list.get(0)+"");
                startActivity(intent);
            }else {
                Toast.makeText(screen.this,"传递的数值为空!",Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
