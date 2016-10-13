package com.mstring.andtest.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mstring.andtest.R;
import com.mstring.andtest.utils.UIHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void vod(View view){
        UIHelper.openLeVodHomeActivty(this);
    }
    public void live(View view){
        UIHelper.openLeLiveHomeActivty(this);
    }
}
