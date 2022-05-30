package com.portpolio.aragada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView btn_hiragana_study, btn_hiragana_test, btn_gatakana_study, btn_gatakana_test, btn_search;
    LinearLayout btn_daara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setBtn();
        search_japanese();
    }

//    카테고리
//    1 = 히라가나, 2 = 가타카나
    void setBtn() {
        btn_hiragana_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStudyActivity(1);
            }
        });
        btn_hiragana_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTestActivity(1);
            }
        });
        btn_gatakana_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStudyActivity(2);
            }
        });
        btn_gatakana_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTestActivity(2);
            }
        });
        btn_daara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChatBotActivity();
            }
        });
    }

    void startStudyActivity(int category) {
        Intent intent = new Intent(MainActivity.this, StudyActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    void startTestActivity(int category) {
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    void startChatBotActivity() {
        Intent intent = new Intent(MainActivity.this, ChatBotActivity.class);
        startActivity(intent);
    }

    public void search_japanese() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://ja.dict.naver.com/#/main");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    void init() {
        btn_hiragana_study = (ImageView) findViewById(R.id.btn_hiragana_study);
        btn_hiragana_test = (ImageView) findViewById(R.id.btn_hiragana_test);
        btn_gatakana_study = (ImageView) findViewById(R.id.btn_gatakana_study);
        btn_gatakana_test = (ImageView) findViewById(R.id.btn_gatakana_test);
        btn_search = (ImageView) findViewById(R.id.btn_search);
        btn_daara = (LinearLayout) findViewById(R.id.btn_daara);
    }
}