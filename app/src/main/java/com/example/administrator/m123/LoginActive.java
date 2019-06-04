package com.example.administrator.m123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tool.IsNetWork;
import tool.SendGet;

public class LoginActive extends AppCompatActivity {
    private TextView regist;
    private TextView login;
    private  TextView forget;
    private EditText username;
    private EditText password;
    public static String result;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(getSupportActionBar() !=null){
//            getSupportActionBar().hide();
//        }
        getSupportActionBar().setTitle("登录");
        setContentView(R.layout.login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        regist = (TextView)findViewById(R.id.regist);
        login = (TextView)findViewById(R.id.login);
        forget = (TextView)findViewById(R.id.forget);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);



        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String usernameStr = username.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();
                if(!isNullOrEmpty(usernameStr) && !isNullOrEmpty(passwordStr)) {
                    if (IsNetWork.isNetWork(LoginActive.this)) {
                        CheckLogin check = new CheckLogin(usernameStr, passwordStr);
                        Thread thread = new Thread(check);
                        thread.start();
                        try {
                            thread.join();
                            if (!"0".equals(result)) {
                                Toast.makeText(LoginActive.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActive.this,Myhome.class);
                                startActivity(intent);
                                LoginActive.this.finish();
                            } else {
                                Toast.makeText(LoginActive.this, "登录失败！请重试", Toast.LENGTH_SHORT).show();
                                password.setText("");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(LoginActive.this, IsNetWork.checkNet(LoginActive.this), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String ss = "";
                    if(isNullOrEmpty(usernameStr)){
                        ss += "用户名,";
                    }
                    if(isNullOrEmpty(passwordStr)){
                        ss += "密码,";
                    }
                    Toast.makeText(LoginActive.this,ss+"不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActive.this, RegistActive.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActive.this, ForgetActive.class);
                startActivity(intent);
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean isNullOrEmpty(String s){
        if(s.equals("") || s == null){
            return true;
        }else{
            return false;
        }
    }
}

class CheckLogin implements Runnable{
    String username;
    String password;

    public CheckLogin(String username,String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        String s = SendGet.SendGet("/QueryServlet/login","username="+username+"&password="+password);
        LoginActive.result = s;
    }
}

