package com.example.corona_disease_module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.corona_disease_module.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.koronaBaslik.setText("Korona Tanı Modülü");
        mBinding.welcomeScreenId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectActivity = new Intent(MainActivity.this,QuestionActivity.class);
                startActivity(redirectActivity);
            }
        });
    }
}