package com.example.administrator.m123;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tool.QueryCol;
import tool.QueryColVed;

public class Myhome extends AppCompatActivity {
    ImageButton Img_mycollect,Img_history,Img_offlinecenter,Img_Set,Img_help,Img_traffic,Img_movieticket,Img_bankcard;
    Button btn_vipVideo,btn_renewalVip,btn_history,btn_mycollect,btn_offlinecenter,btn_setting,btn_help,btn_traffic,btn_movieticket,btn_bankcard,btn_aboutAs,btn_quit;
    TextView txt_login;
    String parameter="";
    public static int statement;
    public static Map<Integer,List<String>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.myhome);
        txt_login=(TextView)findViewById(R.id.txt_login);
        Img_history=(ImageButton)findViewById(R.id.img_history);
        Img_mycollect=(ImageButton)findViewById(R.id.img_mycollect);
        Img_offlinecenter=(ImageButton)findViewById(R.id.img_offlinecenter);
        Img_Set=(ImageButton)findViewById(R.id.img_setting);
        Img_help=(ImageButton)findViewById(R.id.img_help);
        Img_traffic=(ImageButton)findViewById(R.id.img_traffic);
        Img_movieticket=(ImageButton)findViewById(R.id.img_movieticket);
        Img_bankcard=(ImageButton)findViewById(R.id.img_bankcard);

        btn_vipVideo=(Button)findViewById(R.id.btn_vipVideo);
        btn_renewalVip=(Button)findViewById(R.id.btn_renewalVip);
        btn_history=(Button)findViewById(R.id.btn_history);
        btn_mycollect=(Button)findViewById(R.id.btn_mycollect);
        btn_offlinecenter=(Button)findViewById(R.id.btn_offlinecenter);
        btn_setting=(Button)findViewById(R.id.btn_setting);
        btn_help=(Button)findViewById(R.id.btn_help);
        btn_traffic=(Button)findViewById(R.id.btn_traffic);
        btn_movieticket=(Button)findViewById(R.id.btn_movieticket);
        btn_bankcard=(Button)findViewById(R.id.btn_bankcard);
        btn_aboutAs=(Button)findViewById(R.id.btn_aboutAs);
        btn_quit=(Button)findViewById(R.id.btn_quit);


        btn_vipVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Myhome.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_renewalVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow();
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Myhome.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_mycollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new HashMap<>();
                    QueryCol queryCol = new QueryCol(2);
                    Thread thread = new Thread(queryCol);
                    thread.start();
                    try {
                        thread.join();
                        if(statement!=0) {
                            for (int i = 0; i < QueryCol.s.length; i++) {
                                List<String> list = new ArrayList<>();
                                Thread thread1 = new Thread(new QueryColVed(Integer.parseInt(QueryCol.s[i])));
                                thread1.start();
                                thread1.join();
                                for (int j = 0; j < QueryColVed.ss.length; j++) {
                                    list.add(QueryColVed.ss[j]);
                                }
                                map.put(i, list);
                            }
                        }else{
                            Toast.makeText(Myhome.this,"没有收藏记录",Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                Intent intent=new Intent(Myhome.this,collect.class);
                startActivity(intent);
            }
        });
        btn_offlinecenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Myhome.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Myhome.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow();
            }
        });
        btn_traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow();
            }
        });
        btn_movieticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow();
            }
        });
        btn_bankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow();
            }
        });
        btn_aboutAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Myhome.this, Aboutas.class);
                startActivity(intent);
            }
        });


        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_login.setText("点击登录");
            }
        });
        if(parameter.equals("")) {
            if (txt_login.getText().toString().equals("点击登录")) {
                txt_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Myhome.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }else{
            txt_login.setClickable(false);
            btn_quit.setVisibility(View.VISIBLE);
            txt_login.setText(parameter);
        }

        Img_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Myhome.this,MySet.class);
                startActivity(intent);
            }
        });
        Img_mycollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new HashMap<>();
                QueryCol queryCol = new QueryCol(2);
                Thread thread = new Thread(queryCol);
                thread.start();
                try {
                    thread.join();
                    if(statement!=0) {
                        for (int i = 0; i < QueryCol.s.length; i++) {
                            List<String> list = new ArrayList<>();
                            Thread thread1 = new Thread(new QueryColVed(Integer.parseInt(QueryCol.s[i])));
                            thread1.start();
                            thread1.join();
                            for (int j = 0; j < QueryColVed.ss.length; j++) {
                                list.add(QueryColVed.ss[j]);
                            }
                            map.put(i, list);
                        }
                    }else{
                        Toast.makeText(Myhome.this,"没有收藏记录",Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(Myhome.this,collect.class);
                startActivity(intent);

            }
        });
        Img_offlinecenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Myhome.this,MySet.class);
                startActivity(intent);
            }
        });
        Img_Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Myhome.this,MySet.class);
                startActivity(intent);
            }
        });
        Img_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow();
            }
        });
        Img_traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow();
            }
        });
        Img_movieticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow();
            }
        });
        Img_bankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               toastshow();
            }
        });

    }
    private void toastshow(){
        Toast.makeText(Myhome.this,"功能尚未实现,等待中...",Toast.LENGTH_SHORT).show();
    }

}


