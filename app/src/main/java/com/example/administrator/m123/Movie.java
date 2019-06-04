package com.example.administrator.m123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import tool.QueryVedThred;

public class Movie extends AppCompatActivity {
    ImageButton btnn1, btnn2, btnn3, btnn4;
    Button btn11;
    TextView textfczlm, textgtxone, textgtxtwo, textxchh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.movie);
        btnn1 = (ImageButton) findViewById(R.id.imagefczlm);
        btnn2 = (ImageButton) findViewById(R.id.imagegtxone);
        btnn3 = (ImageButton) findViewById(R.id.imagegtxtwo);
        btnn4 = (ImageButton) findViewById(R.id.imagexchh);
        btn11 = (Button) findViewById(R.id.moviescreen);
        textfczlm = (TextView) findViewById(R.id.textfczlm);
        btnn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textfczlm);
            }
        });
        textgtxone = (TextView) findViewById(R.id.textgtxone);
        //为button绑定事件
        btnn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                s10( textgtxone);
            }
        });
        textgtxtwo = (TextView) findViewById(R.id.textgtxtwo);
        //为button绑定事件
        btnn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textgtxtwo);
            }
        });
        textxchh = (TextView) findViewById(R.id.textxchh);
        btnn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(textxchh);
            }
        });
        btn11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Movie.this, screen.class);
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
                Intent intent = new Intent(Movie.this, Play.class);
                intent.putExtra("showdata", QueryVedThred.list.get(0)+"");
                startActivity(intent);
            }else {
                Toast.makeText(Movie.this,"传递的数值为空!",Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
