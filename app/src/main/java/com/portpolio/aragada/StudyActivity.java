package com.portpolio.aragada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class StudyActivity extends AppCompatActivity {

    TextView tv_original;
    ImageView btn_search, btn_backHome;
    AppCompatButton[] btn_option = new AppCompatButton[4];
    int category, correct;
    String[] original = new String[48], pronunciation = new String[48];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        initActivity();
        setPronunciation();
        setNewQuestion();
        search_japanese();
        backHome();

    }

    void chooseOption(int choose) {
        if (choose == correct) { // 정답을 맞췄을 경우
            btn_option[choose].setText("o");
            btn_option[choose].setTextColor(Color.parseColor("#ffffff"));
            btn_option[choose].setBackgroundResource(R.drawable.study_correct);

            for (int i = 0; i < btn_option.length; i++) {
                btn_option[i].setClickable(false);
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setNewQuestion();
                }
            }, 1500);
        } else {
            btn_option[choose].setText("x");
            btn_option[choose].setTextColor(Color.parseColor("#ffffff"));
            btn_option[choose].setBackgroundResource(R.drawable.study_wrong);
        }
    }

    void setNewQuestion() {
        resetUi(); // ui 초기화

        Random random = new Random();
        int index = random.nextInt(46); // 일본어 index
        correct = random.nextInt(4); // 정답 자리 index

        tv_original.setText(original[index]);
        btn_option[correct].setText(pronunciation[index]);

        for (int i = 0; i < btn_option.length; i++) {
            if (i != correct) {
                btn_option[i].setText(pronunciation[random.nextInt(46)]);
            }
        }
    }

    void resetUi() {
        for (int i = 0; i < btn_option.length; i++) {
            btn_option[i].setBackgroundResource(R.drawable.study_base);
            btn_option[i].setText("");
            btn_option[i].setTextColor(Color.parseColor("#B8E6E1"));
            btn_option[i].setClickable(true);

        }
    }

    void backHome() {
        btn_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void initActivity() {
        tv_original = (TextView) findViewById(R.id.tv_original);
        btn_option[0] = (AppCompatButton) findViewById(R.id.btn_option1);
        btn_option[1] = (AppCompatButton) findViewById(R.id.btn_option2);
        btn_option[2] = (AppCompatButton) findViewById(R.id.btn_option3);
        btn_option[3] = (AppCompatButton) findViewById(R.id.btn_option4);
        btn_search = (ImageView) findViewById(R.id.btn_search);
        btn_backHome = (ImageView) findViewById(R.id.btn_backHome);

        Intent intent = getIntent();
        category = intent.getIntExtra("category", 1);
        if (category == 1) {
            setHiragana();
        } else if(category == 2) {
            setGatakana();
        } else {
            finish();
        }

        for (int i = 0; i < btn_option.length; i++) {
            int finalI = i;
            btn_option[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chooseOption(finalI);
                }
            });
        }
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

    void setPronunciation() {
        pronunciation[0] = "아";
        pronunciation[1] = "이";
        pronunciation[2] = "우";
        pronunciation[3] = "에";
        pronunciation[4] = "오";

        pronunciation[5] = "카";
        pronunciation[6] = "키";
        pronunciation[7] = "쿠";
        pronunciation[8] = "케";
        pronunciation[9] = "코";

        pronunciation[10] = "사";
        pronunciation[11] = "시";
        pronunciation[12] = "스";
        pronunciation[13] = "세";
        pronunciation[14] = "소";

        pronunciation[15] = "타";
        pronunciation[16] = "치";
        pronunciation[17] = "츠";
        pronunciation[18] = "테";
        pronunciation[19] = "토";

        pronunciation[20] = "나";
        pronunciation[21] = "니";
        pronunciation[22] = "누";
        pronunciation[23] = "네";
        pronunciation[24] = "노";

        pronunciation[25] = "하";
        pronunciation[26] = "히";
        pronunciation[27] = "후";
        pronunciation[28] = "헤";
        pronunciation[29] = "호";

        pronunciation[30] = "마";
        pronunciation[31] = "미";
        pronunciation[32] = "무";
        pronunciation[33] = "메";
        pronunciation[34] = "모";

        pronunciation[35] = "야";
        pronunciation[36] = "유";
        pronunciation[37] = "요";

        pronunciation[38] = "라";
        pronunciation[39] = "리";
        pronunciation[40] = "루";
        pronunciation[41] = "레";
        pronunciation[42] = "로";

        pronunciation[43] = "와";
        pronunciation[44] = "오";
        pronunciation[45] = "은";
    }

    void setHiragana() {
        original[0] = "あ";
        original[1] = "い";
        original[2] = "う";
        original[3] = "え";
        original[4] = "お";

        original[5] = "か";
        original[6] = "き";
        original[7] = "く";
        original[8] = "け";
        original[9] = "こ";

        original[10] = "さ";
        original[11] = "し";
        original[12] = "す";
        original[13] = "せ";
        original[14] = "そ";

        original[15] = "た";
        original[16] = "ち";
        original[17] = "つ";
        original[18] = "て";
        original[19] = "と";

        original[20] = "な";
        original[21] = "に";
        original[22] = "ぬ";
        original[23] = "ね";
        original[24] = "の";

        original[25] = "は";
        original[26] = "ひ";
        original[27] = "ふ";
        original[28] = "へ";
        original[29] = "ほ";

        original[30] = "ま";
        original[31] = "み";
        original[32] = "む";
        original[33] = "め";
        original[34] = "も";

        original[35] = "や";
        original[36] = "ゆ";
        original[37] = "よ";

        original[38] = "ら";
        original[39] = "り";
        original[40] = "る";
        original[41] = "れ";
        original[42] = "ろ";

        original[43] = "わ";
        original[44] = "を";
        original[45] = "ん";
    }

    void setGatakana() {
        original[0] = "ア";
        original[1] = "イ";
        original[2] = "ウ";
        original[3] = "エ";
        original[4] = "オ";

        original[5] = "カ";
        original[6] = "キ";
        original[7] = "ク";
        original[8] = "ケ";
        original[9] = "コ";

        original[10] = "サ";
        original[11] = "シ";
        original[12] = "ス";
        original[13] = "セ";
        original[14] = "ソ";

        original[15] = "タ";
        original[16] = "チ";
        original[17] = "ツ";
        original[18] = "テ";
        original[19] = "ト";

        original[20] = "ナ";
        original[21] = "ニ";
        original[22] = "ヌ";
        original[23] = "ネ";
        original[24] = "ノ";

        original[25] = "ハ";
        original[26] = "ヒ";
        original[27] = "フ";
        original[28] = "ヘ";
        original[29] = "ホ";

        original[30] = "マ";
        original[31] = "ミ";
        original[32] = "ム";
        original[33] = "メ";
        original[34] = "モ";

        original[35] = "ヤ";
        original[36] = "ユ";
        original[37] = "ヨ";

        original[38] = "ラ";
        original[39] = "リ";
        original[40] = "ル";
        original[41] = "レ";
        original[42] = "ロ";

        original[43] = "ワ";
        original[44] = "ヲ";
        original[45] = "ン";
    }


}