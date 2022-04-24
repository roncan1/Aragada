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
    String[] arrayOriginal = new String[48], arrayPronunciation = new String[48];


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

        tv_original.setText(arrayOriginal[index]);
        btn_option[correct].setText(arrayPronunciation[index]);

        for (int i = 0; i < btn_option.length; i++) {
            if (i != correct) {
                btn_option[i].setText(arrayPronunciation[random.nextInt(46)]);
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
        arrayPronunciation[0] = "아";
        arrayPronunciation[1] = "이";
        arrayPronunciation[2] = "우";
        arrayPronunciation[3] = "에";
        arrayPronunciation[4] = "오";

        arrayPronunciation[5] = "카";
        arrayPronunciation[6] = "키";
        arrayPronunciation[7] = "쿠";
        arrayPronunciation[8] = "케";
        arrayPronunciation[9] = "코";

        arrayPronunciation[10] = "사";
        arrayPronunciation[11] = "시";
        arrayPronunciation[12] = "스";
        arrayPronunciation[13] = "세";
        arrayPronunciation[14] = "소";

        arrayPronunciation[15] = "타";
        arrayPronunciation[16] = "치";
        arrayPronunciation[17] = "츠";
        arrayPronunciation[18] = "테";
        arrayPronunciation[19] = "토";

        arrayPronunciation[20] = "나";
        arrayPronunciation[21] = "니";
        arrayPronunciation[22] = "누";
        arrayPronunciation[23] = "네";
        arrayPronunciation[24] = "노";

        arrayPronunciation[25] = "하";
        arrayPronunciation[26] = "히";
        arrayPronunciation[27] = "후";
        arrayPronunciation[28] = "헤";
        arrayPronunciation[29] = "호";

        arrayPronunciation[30] = "마";
        arrayPronunciation[31] = "미";
        arrayPronunciation[32] = "무";
        arrayPronunciation[33] = "메";
        arrayPronunciation[34] = "모";

        arrayPronunciation[35] = "야";
        arrayPronunciation[36] = "유";
        arrayPronunciation[37] = "요";

        arrayPronunciation[38] = "라";
        arrayPronunciation[39] = "리";
        arrayPronunciation[40] = "루";
        arrayPronunciation[41] = "레";
        arrayPronunciation[42] = "로";

        arrayPronunciation[43] = "와";
        arrayPronunciation[44] = "오";
        arrayPronunciation[45] = "은";
    }

    void setHiragana() {
        arrayOriginal[0] = "あ";
        arrayOriginal[1] = "い";
        arrayOriginal[2] = "う";
        arrayOriginal[3] = "え";
        arrayOriginal[4] = "お";

        arrayOriginal[5] = "か";
        arrayOriginal[6] = "き";
        arrayOriginal[7] = "く";
        arrayOriginal[8] = "け";
        arrayOriginal[9] = "こ";

        arrayOriginal[10] = "さ";
        arrayOriginal[11] = "し";
        arrayOriginal[12] = "す";
        arrayOriginal[13] = "せ";
        arrayOriginal[14] = "そ";

        arrayOriginal[15] = "た";
        arrayOriginal[16] = "ち";
        arrayOriginal[17] = "つ";
        arrayOriginal[18] = "て";
        arrayOriginal[19] = "と";

        arrayOriginal[20] = "な";
        arrayOriginal[21] = "に";
        arrayOriginal[22] = "ぬ";
        arrayOriginal[23] = "ね";
        arrayOriginal[24] = "の";

        arrayOriginal[25] = "は";
        arrayOriginal[26] = "ひ";
        arrayOriginal[27] = "ふ";
        arrayOriginal[28] = "へ";
        arrayOriginal[29] = "ほ";

        arrayOriginal[30] = "ま";
        arrayOriginal[31] = "み";
        arrayOriginal[32] = "む";
        arrayOriginal[33] = "め";
        arrayOriginal[34] = "も";

        arrayOriginal[35] = "や";
        arrayOriginal[36] = "ゆ";
        arrayOriginal[37] = "よ";

        arrayOriginal[38] = "ら";
        arrayOriginal[39] = "り";
        arrayOriginal[40] = "る";
        arrayOriginal[41] = "れ";
        arrayOriginal[42] = "ろ";

        arrayOriginal[43] = "わ";
        arrayOriginal[44] = "を";
        arrayOriginal[45] = "ん";
    }

    void setGatakana() {
        arrayOriginal[0] = "ア";
        arrayOriginal[1] = "イ";
        arrayOriginal[2] = "ウ";
        arrayOriginal[3] = "エ";
        arrayOriginal[4] = "オ";

        arrayOriginal[5] = "カ";
        arrayOriginal[6] = "キ";
        arrayOriginal[7] = "ク";
        arrayOriginal[8] = "ケ";
        arrayOriginal[9] = "コ";

        arrayOriginal[10] = "サ";
        arrayOriginal[11] = "シ";
        arrayOriginal[12] = "ス";
        arrayOriginal[13] = "セ";
        arrayOriginal[14] = "ソ";

        arrayOriginal[15] = "タ";
        arrayOriginal[16] = "チ";
        arrayOriginal[17] = "ツ";
        arrayOriginal[18] = "テ";
        arrayOriginal[19] = "ト";

        arrayOriginal[20] = "ナ";
        arrayOriginal[21] = "ニ";
        arrayOriginal[22] = "ヌ";
        arrayOriginal[23] = "ネ";
        arrayOriginal[24] = "ノ";

        arrayOriginal[25] = "ハ";
        arrayOriginal[26] = "ヒ";
        arrayOriginal[27] = "フ";
        arrayOriginal[28] = "ヘ";
        arrayOriginal[29] = "ホ";

        arrayOriginal[30] = "マ";
        arrayOriginal[31] = "ミ";
        arrayOriginal[32] = "ム";
        arrayOriginal[33] = "メ";
        arrayOriginal[34] = "モ";

        arrayOriginal[35] = "ヤ";
        arrayOriginal[36] = "ユ";
        arrayOriginal[37] = "ヨ";

        arrayOriginal[38] = "ラ";
        arrayOriginal[39] = "リ";
        arrayOriginal[40] = "ル";
        arrayOriginal[41] = "レ";
        arrayOriginal[42] = "ロ";

        arrayOriginal[43] = "ワ";
        arrayOriginal[44] = "ヲ";
        arrayOriginal[45] = "ン";
    }


}