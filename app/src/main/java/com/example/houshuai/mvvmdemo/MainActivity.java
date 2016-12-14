package com.example.houshuai.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.houshuai.mvvmdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding url = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewModle viewModle = new ViewModle(getResources(), this);
        url.setUrl(viewModle);

    }
}
