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

public class ForgetActive extends AppCompatActivity {
    private EditText registEmail1;
    private EditText registyzm1;
    private TextView btn_yzm1;
    private EditText password1;
    private TextView regist1;
    private static String code;
    static String result3;
    static String result2;
    static String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("密码修改");
        setContentView(R.layout.activity_forget);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        registEmail1 = (EditText)findViewById(R.id.registEmail1);
        registyzm1 = (EditText)findViewById(R.id.registyzm1);
        btn_yzm1 = (TextView)findViewById(R.id.btn_yzm1);
        password1 = (EditText)findViewById(R.id.password1);
        regist1 = (TextView)findViewById(R.id.regist1);

        btn_yzm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailStr = registEmail1.getText().toString().trim();
                if("".equals(EmailStr)){
                    Toast.makeText(ForgetActive.this,"邮箱不能输入为空！",Toast.LENGTH_SHORT).show();
                }else {
                    if (IsNetWork.isNetWork(ForgetActive.this)) {
                        CheckEmail1 cemail = new CheckEmail1(EmailStr);
                        Thread temail = new Thread(cemail);
                        temail.start();
                        try {
                            temail.join();
                            if ("1".equals(result3)) {
                                if (!checkEmail(EmailStr)) {
                                    Toast.makeText(ForgetActive.this, "邮箱格式不正确，请重新检查", Toast.LENGTH_SHORT).show();
                                } else {
                                    code = getCode();
                                    SendMail1 sendemail = new SendMail1(EmailStr, code);
                                    Thread thread = new Thread(sendemail);
                                    thread.start();
                                    try {
                                        thread.join();
                                        if ("1".equals(result)) {
                                            Toast.makeText(ForgetActive.this, "邮件发送成功！", Toast.LENGTH_SHORT).show();
                                            CountDownTimer timer = new CountDownTimer(60000, 1000) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                    btn_yzm1.setEnabled(false);
                                                    btn_yzm1.setTextColor(Color.GRAY);
                                                    btn_yzm1.setText("已发送(" + millisUntilFinished / 1000 + ")");
                                                }
                                                @Override
                                                public void onFinish() {
                                                    btn_yzm1.setEnabled(true);
                                                    btn_yzm1.setTextColor(Color.parseColor("#628b34"));
                                                    btn_yzm1.setText("重新获取");
                                                }
                                            }.start();
                                        } else {
                                            Toast.makeText(ForgetActive.this, "失败！请检查邮箱地址是否正确！", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }else{
                                Toast.makeText(ForgetActive.this,"邮箱不存在，请注册",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgetActive.this, RegistActive.class);
                                startActivity(intent);
                                ForgetActive.this.finish();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(ForgetActive.this, IsNetWork.checkNet(ForgetActive.this), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        regist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailStr = registEmail1.getText().toString().trim();
                String codeStr = registyzm1.getText().toString().trim();
                String passwordStr = password1.getText().toString().trim();
                String message = "";

                if("".equals(EmailStr)){
                    message += "邮箱,";
                }
                if("".equals(codeStr)){
                    message += "验证码,";
                }
                if("".equals(passwordStr)){
                    message += "密码,";
                }
                if(!isNullOrEmpty(EmailStr) && !isNullOrEmpty(codeStr) && !isNullOrEmpty(passwordStr)){
                    if(IsNetWork.isNetWork(ForgetActive.this)) {
                        if(String.valueOf(code).equals(codeStr)){
                            UpdatePassword update = new UpdatePassword(EmailStr,passwordStr);
                            Thread thread = new Thread(update);
                            thread.start();
                            try {
                                thread.join();
                                if("1".equals(result2)){
                                    Toast.makeText(ForgetActive.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgetActive.this,LoginActive.class);
                                    startActivity(intent);
                                    ForgetActive.this.finish();
                                }else{
                                    Toast.makeText(ForgetActive.this, "修改失败,请重试！", Toast.LENGTH_SHORT).show();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(ForgetActive.this, "验证码错误！", Toast.LENGTH_SHORT).show();
                            registyzm1.setText("");
                        }
                    }else{
                        Toast.makeText(ForgetActive.this,IsNetWork.checkNet(ForgetActive.this),Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ForgetActive.this,message+"不能为空！",Toast.LENGTH_SHORT).show();
                }
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
class CheckEmail1 implements Runnable{
    String email;
    public CheckEmail1(String email){
        this.email = email;
    }
    @Override
    public void run() {
        ForgetActive.result3 = SendGet.SendGet("/CheckServlet/email","Email="+email);
    }
}
class SendMail1 implements Runnable{

    private String email;
    private String code;

    public SendMail1(String email,String code){
        this.email = email;
        this.code = code;
    }

    @Override
    public void run() {
        ForgetActive.result = SendGet.SendGet("/Send","email="+email+"&code="+code);
    }
}
class UpdatePassword implements Runnable{
    private String emailStr;
    private String passwordStr;
    public UpdatePassword(String emailStr,String passwordStr){
        this.emailStr = emailStr;
        this.passwordStr = passwordStr;
    }
    @Override
    public void run() {
        ForgetActive.result2 = SendGet.SendGet("/UpdateServlet/login","Email="+emailStr+"&password="+passwordStr);
    }
}
