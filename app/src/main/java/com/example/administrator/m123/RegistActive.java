package com.example.administrator.m123;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tool.IsNetWork;
import tool.SendGet;

public class RegistActive extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText repassword;
    private EditText Email;
    private EditText yzm;
    private TextView btn_regist;
    private TextView btn_yzm;
    private TextView visitor_login;
    static String result;
    static String result1;
    static String result2;
    static String result3;
    private static String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("注册");
        setContentView(R.layout.activity_regist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = (EditText)findViewById(R.id.registusername);
        password = (EditText)findViewById(R.id.registpassword);
        repassword = (EditText)findViewById(R.id.confirmpassword);
        Email = (EditText)findViewById(R.id.registEmail);
        yzm = (EditText)findViewById(R.id.registyzm);
        btn_regist = (TextView)findViewById(R.id.btn_regist);
        btn_yzm = (TextView)findViewById(R.id.btn_yzm);
        visitor_login = (TextView)findViewById(R.id.visitor_login);

        btn_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailStr = Email.getText().toString().trim();
                if("".equals(EmailStr)){
                    Toast.makeText(RegistActive.this,"邮箱不能输入为空！",Toast.LENGTH_SHORT).show();
                }else {
                    if (IsNetWork.isNetWork(RegistActive.this)) {
                    CheckEmail cemail = new CheckEmail(EmailStr);
                    Thread temail = new Thread(cemail);
                    temail.start();
                    try {
                        temail.join();
                        if ("0".equals(result3)) {
                            if (!checkEmail(EmailStr)) {
                                Toast.makeText(RegistActive.this, "邮箱格式不正确，请重新检查", Toast.LENGTH_SHORT).show();
                            } else {
                                    code = getCode();
                                    SendMail sendemail = new SendMail(EmailStr, code);
                                    Thread thread = new Thread(sendemail);
                                    thread.start();
                                    try {
                                        thread.join();
                                        if ("1".equals(result)) {
                                            Toast.makeText(RegistActive.this, "邮件发送成功！", Toast.LENGTH_SHORT).show();
                                            CountDownTimer timer = new CountDownTimer(60000, 1000) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                    btn_yzm.setEnabled(false);
                                                    btn_yzm.setTextColor(Color.GRAY);
                                                    btn_yzm.setText("已发送(" + millisUntilFinished / 1000 + ")");
                                                }

                                                @Override
                                                public void onFinish() {
                                                    btn_yzm.setEnabled(true);
                                                    btn_yzm.setTextColor(Color.parseColor("#628b34"));
                                                    btn_yzm.setText("重新获取");
                                                }
                                            }.start();
                                        } else {
                                            Toast.makeText(RegistActive.this, "失败！请检查邮箱地址是否正确！", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                            }
                        }else{
                            Toast.makeText(RegistActive.this,"邮箱已经存在，请直接登录",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistActive.this,LoginActive.class);
                            startActivity(intent);
                            RegistActive.this.finish();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    } else {
                    Toast.makeText(RegistActive.this, IsNetWork.checkNet(RegistActive.this), Toast.LENGTH_SHORT).show();
                }
                }
            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = username.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();
                String repasswordStr = repassword.getText().toString().trim();
                String EmailStr = Email.getText().toString().trim();
                String yzmStr = yzm.getText().toString().trim();

                String message = "";
                if(isNullOrEmpty(usernameStr)){
                    message+="用户名,";
                }
                if(isNullOrEmpty(passwordStr)){
                    message+="密码,";
                }
                if(isNullOrEmpty(repasswordStr)){
                    message+="重复密码,";
                }
                if(isNullOrEmpty(EmailStr)){
                    message+="邮箱,";
                }
                if(isNullOrEmpty(yzmStr)){
                    message+="验证码,";
                }
                if(!isNullOrEmpty(usernameStr) && !isNullOrEmpty(passwordStr) && !isNullOrEmpty(repasswordStr) && !isNullOrEmpty(EmailStr) && !isNullOrEmpty(yzmStr)){
                    if(IsNetWork.isNetWork(RegistActive.this)) {
                        CheckUsername checkUsername = new CheckUsername(usernameStr);
                        Thread thread = new Thread(checkUsername);
                        thread.start();
                        try {
                            thread.join();
                            if ("0".equals(result1)) {
                                if (passwordStr.equals(repasswordStr)) {
                                    if (String.valueOf(code).equals(yzmStr)) {
                                        RegistLogin registLogin = new RegistLogin(usernameStr, passwordStr, EmailStr);
                                        Thread thread1 = new Thread(registLogin);
                                        thread1.start();
                                        thread1.join();
                                        if ("1".equals(result2)) {
                                            Toast.makeText(RegistActive.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegistActive.this,LoginActive.class);
                                            startActivity(intent);
                                            RegistActive.this.finish();
                                        } else {
                                            Toast.makeText(RegistActive.this, "注册失败，请重新尝试！", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(RegistActive.this, "验证码错误！", Toast.LENGTH_SHORT).show();
                                        yzm.setText("");
                                    }
                                } else {
                                    Toast.makeText(RegistActive.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                                    repassword.setText("");
                                }
                            } else {
                                Toast.makeText(RegistActive.this, "用户名已存在!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(RegistActive.this,IsNetWork.checkNet(RegistActive.this),Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistActive.this,message+"不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        visitor_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistActive.this,"功能开发中，请等待。。。。",Toast.LENGTH_SHORT).show();
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
    private boolean checkEmail(String email){
        boolean result;
        String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
        result = email.matches(regex);
        return result;
    }
    private String getCode(){
        String code = null;
        int a = (int)(Math.random()*1000000)+1000;
        code = String.valueOf(a).substring(1,5).trim();
        return code;
    }
    private boolean isNullOrEmpty(String s){
        if(s.equals("") || s == null){
            return true;
        }else{
            return false;
        }
    }
}

class SendMail implements Runnable{

    private String email;
    private String code;

    public SendMail(String email,String code){
        this.email = email;
        this.code = code;
    }

    @Override
    public void run() {
       RegistActive.result = SendGet.SendGet("/Send","email="+email+"&code="+code);
    }
}

class CheckUsername implements Runnable{
    String username;

    public CheckUsername(String username){
        this.username = username;
    }

    @Override
    public void run() {
        RegistActive.result1 = SendGet.SendGet("/CheckServlet/login","username="+username);
    }
}
class CheckEmail implements Runnable{
    String email;

    public CheckEmail(String email){
        this.email = email;
    }

    @Override
    public void run() {
        RegistActive.result3 = SendGet.SendGet("/CheckServlet/email","Email="+email);
    }
}

class RegistLogin implements Runnable{
    String username;
    String password;
    String email;

    public RegistLogin(String username,String password,String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public void run() {
        RegistActive.result2 = SendGet.SendGet("/InsertServlet/login","username="+username+"&password="+password+"&Email="+email);
    }
}