package com.example.administrator.m123;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowPlay extends Activity {
    TextView txtshow;
    @Override
    public void onCreate(Bundle saveInstancetate) {
        super.onCreate(saveInstancetate);
        setContentView(R.layout.showlayout);
        Intent intent = getIntent();
        String showdata = intent.getStringExtra("showdata");
        txtshow=(TextView)findViewById(R.id.txtshow1);
        txtshow.setText(showdata);
    }
}
