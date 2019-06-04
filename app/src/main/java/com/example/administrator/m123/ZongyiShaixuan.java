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
import tool.SendGet;
import static tool.StringDeal.StringDeal;

public class ZongyiShaixuan extends Activity {
    ImageView bttnx1,bttnx2,bttnx3,bttnx4;
    TextView mTextView,screenTextView1,screenTextView2,screenTextView3,screenTextView4;
    Button btnx1,btnx2,btnx3,btnx4;
    public static String string;
    static List<String> list = new ArrayList<String>();
    static int result=1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zongyishaixuan);
        bttnx1 = (ImageView)findViewById(R.id.zongyi1);
        bttnx2 = (ImageView)findViewById(R.id.zongyi2);
        bttnx3 = (ImageView)findViewById(R.id.zongyi3);
        bttnx4 = (ImageView)findViewById(R.id.zongyi4);
        screenTextView1 = (TextView)findViewById(R.id.screenzongyi1);
        screenTextView2 = (TextView)findViewById(R.id.screenzongyi2);
        screenTextView3 = (TextView)findViewById(R.id.screenzongyi3);
        screenTextView4 = (TextView)findViewById(R.id.screenzongyi4);
        btnx1 = (Button) findViewById(R.id.screen1);
        btnx2 = (Button) findViewById(R.id.screen2);
        btnx3 = (Button) findViewById(R.id.screen3);
        btnx4 = (Button) findViewById(R.id.screen4);
        btnx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryVedThred send = new QueryVedThred("","综艺","",btnx1.getText().toString().trim());
                Thread thread = new Thread(send);
                thread.start();
                try {
                    thread.join();
                    if(QueryVedThred.result != 0) {
                        QueryVedDetailThred send1 = new QueryVedDetailThred(QueryVedThred.list);
                        Thread thread1 = new Thread(send1);
                        thread1.start();
                        thread1.join();
                        clear();
                        Glide.with(ZongyiShaixuan.this).load(QueryVedDetailThred.map.get(0).get(3)).into(bttnx1);
                        screenTextView1.setText(QueryVedDetailThred.map.get(0).get(0));
                    }else{
                        Toast.makeText(ZongyiShaixuan.this,"查询结果为空!",Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btnx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryVedThred send = new QueryVedThred("","综艺","",btnx2.getText().toString().trim());
                Thread thread = new Thread(send);
                thread.start();
                try {
                    thread.join();
                    if(QueryVedThred.result != 0) {
                        QueryVedDetailThred send1 = new QueryVedDetailThred(QueryVedThred.list);
                        Thread thread1 = new Thread(send1);
                        thread1.start();
                        thread1.join();
                        clear();
                        Glide.with(ZongyiShaixuan.this).load(QueryVedDetailThred.map.get(0).get(3)).into(bttnx1);
                        screenTextView1.setText(QueryVedDetailThred.map.get(0).get(0));

                    }else{
                        Toast.makeText(ZongyiShaixuan.this,"查询结果为空!",Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btnx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryVedThred send = new QueryVedThred("","综艺","",btnx3.getText().toString().trim());
                Thread thread = new Thread(send);
                thread.start();
                try {
                    thread.join();
                    if(QueryVedThred.result != 0) {
                        QueryVedDetailThred send1 = new QueryVedDetailThred(QueryVedThred.list);
                        Thread thread1 = new Thread(send1);
                        thread1.start();
                        thread1.join();
                        clear();
                        Glide.with(ZongyiShaixuan.this).load(QueryVedDetailThred.map.get(0).get(3)).into(bttnx1);
                        screenTextView1.setText(QueryVedDetailThred.map.get(0).get(0));
                    }else{
                        Toast.makeText(ZongyiShaixuan.this,"查询结果为空!",Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btnx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryVedThred send = new QueryVedThred("","综艺","",btnx4.getText().toString().trim());
                Thread thread = new Thread(send);
                thread.start();
                try {
                    thread.join();
                    if(QueryVedThred.result != 0) {
                        QueryVedDetailThred send1 = new QueryVedDetailThred(QueryVedThred.list);
                        Thread thread1 = new Thread(send1);
                        thread1.start();
                        thread1.join();
                        clear();
                        Glide.with(ZongyiShaixuan.this).load(QueryVedDetailThred.map.get(0).get(3)).into(bttnx1);
                        screenTextView1.setText(QueryVedDetailThred.map.get(0).get(0));
                    }else{
                        Toast.makeText(ZongyiShaixuan.this,"查询结果为空!",Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bttnx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(screenTextView1);
            }
        });
        bttnx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s10(screenTextView2);
            }
        });
        bttnx3.setOnClickListener(new View.OnClickListener() {
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
        bttnx1.setImageDrawable(null);
        bttnx2.setImageDrawable(null);
        bttnx3.setImageDrawable(null);
        bttnx4.setImageDrawable(null);
    }
    private void s10(TextView text){
        Thread thread = new Thread(new QueryVedThred(text.getText().toString().trim(),"","",""));
        thread.start();
        try {
            thread.join();
            if(QueryVedThred.result!=0) {
                Intent intent = new Intent(ZongyiShaixuan.this, Play.class);
                intent.putExtra("showdata", QueryVedThred.list.get(0)+"");
                startActivity(intent);
            }else {
                Toast.makeText(ZongyiShaixuan.this,"传递的数值为空!",Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
