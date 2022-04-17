package com.portpolio.aragada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView btn_hiragana_study, btn_hiragana_test, btn_gatakana_study, btn_gatakana_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setBtn();
    }

//    카테고리
//    1 = 히라가나, 2 = 가타카나
    void setBtn() {
        btn_hiragana_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStudy(1);
            }
        });
        btn_hiragana_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTest(1);
            }
        });
        btn_gatakana_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStudy(2);
            }
        });
        btn_gatakana_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTest(2);
            }
        });
    }

    void startStudy(int category) {
        Intent intent = new Intent(MainActivity.this, StudyActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    void startTest(int category) {
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    void init() {
        btn_hiragana_study = (ImageView) findViewById(R.id.btn_hiragana_study);
        btn_hiragana_test = (ImageView) findViewById(R.id.btn_hiragana_test);
        btn_gatakana_study = (ImageView) findViewById(R.id.btn_gatakana_study);
        btn_gatakana_test = (ImageView) findViewById(R.id.btn_gatakana_test);
    }
}