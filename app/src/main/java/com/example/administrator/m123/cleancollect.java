package com.example.administrator.m123;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
public class cleancollect extends Activity {
    LinearLayout mainLayout;
    public void onclick(View v) {
        mainLayout.removeAllViews();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collectfragment);
        mainLayout = (LinearLayout)findViewById(R.id.allclean);
    }
}
