package com.portpolio.aragada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TestActivity extends AppCompatActivity {

    ImageView btn_search, btn_backHome;
    TextView tv_rowName;
    TextView[] tv_target = new TextView[5];
    AppCompatButton[] btnOption = new AppCompatButton[46];
    int category, rowIndex = 0, correctIndex = 0, targetNum = 0;
    String correctValue;
    String[] arrayOriginal = new String[46],
             arrayRowName = new String[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        search_japanese();
        backHome();
        setOnClick();
    }

    public void setCorrectValue(int correctIndex) {
        this.correctValue = arrayOriginal[correctIndex];
    }

    public String getCorrectValue(int correctIndex) {
        setCorrectValue(correctIndex);
        return correctValue;
    }

    void checkCorrect(int index) {
        String btnStringTmp = btnOption[index].getText().toString(); // 클릭된 버튼의 text 가져오기
        if (btnStringTmp == getCorrectValue(correctIndex)) { // 버튼이 정답일 경우
            btnOption[index].setVisibility(View.GONE); // 눌러진 버튼 없애기
            correctIndex++; // 정답인덱스 +1
            if (targetNum == 4) { // 다음 행으로 넘기기
                Log.d("TestActivity :", "targetNum(a) : " + targetNum);
                targetNum = 0;
                rowIndex++;
                setNewRow(rowIndex);
            } else if (correctIndex == 45) {
//                모든것이 끝났을 때 효과주기
                testClear();
            } else {
                Log.d("TestActivity :", "targetNum(b) : " + targetNum);
                setBtnCorrect(tv_target[targetNum]);
                targetNum++;
                setBtnTarget(tv_target[targetNum]);
                setCorrectValue(correctIndex);
            }
        }  else { // 버튼이 정답이 아닐경우
            Log.d("TestActivity :", "targetNum(c) : " + targetNum);
            setBtnWrong(tv_target[targetNum]); // tv_target[targetNum]을 setWrong()
            for (int i = 0; i < btnOption.length; i++) { // 모든 버튼 클릭 차단
                final int a = i;
                btnOption[i].setClickable(false);
            }
            new Handler().postDelayed(new Runnable() // 딜레이 후 원상복귀
            {
                @Override
                public void run()
                {
                    for (int i = 0; i < btnOption.length; i++) {
                        final int a = i;
                        btnOption[i].setClickable(true);
                    }
                    setBtnTarget(tv_target[targetNum]);
                }
            }, 600);
        }
    }

    private void testClear() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("축하합니다!")
                .setContentText("모든 테스트를 통과하셨습니다!")
                .setConfirmText("끝내기")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                })
                .show();
    }

    void setOnClick() {
        for (int i = 0; i < btnOption.length; i++) {
            final int index = i;
            btnOption[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkCorrect(index);
                }
            });
        }
    }

    void setNewRow(int rowNum) {
        tv_rowName.setText(arrayRowName[rowNum]);
        setBtnTarget(tv_target[0]);
        setBtnUnresolved(tv_target[1]);
        setBtnUnresolved(tv_target[2]);
        setBtnUnresolved(tv_target[3]);
        setBtnUnresolved(tv_target[4]);
    }

    void setBtnTarget(TextView tv) {
        tv.setText(" ");
        tv.setBackgroundResource(R.drawable.test_target);
    }

    void setBtnUnresolved(TextView tv) {
        tv.setText(" ");
        tv.setBackgroundResource(R.drawable.test_unresolved);
    }

    void setBtnWrong(TextView tv) {
        tv.setText(" ");
        tv.setBackgroundResource(R.drawable.test_wrong);
    }

    void setBtnCorrect(TextView tv) {
        tv.setText(correctValue);
        tv.setBackgroundResource(R.drawable.test_correct);
    }

    void backHome() {
        btn_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    void initBtnOpt() {
        int[] numbers = new int[46];
        for(int insertCur = 0; insertCur < numbers.length ; insertCur++){
            numbers[insertCur] = (int)(Math.random() * 46);
            for(int searchCur = 0; searchCur < insertCur; searchCur ++){
                if(numbers[insertCur] == numbers[searchCur]){
                    insertCur--;
                    break;
                }
            }
        }

        for (int i = 0; i < 46; i++) {
            btnOption[i].setText(arrayOriginal[numbers[i]]);
        }
    }

    void init() {
        Intent intent = getIntent();
        category = intent.getIntExtra("category", 1);

        if (category == 1) {
            setHiragana();
        } else if(category == 2) {
            setGatakana();
        } else {
            finish();
        }

        btn_backHome = (ImageView) findViewById(R.id.btn_backHome);
        btn_search = (ImageView) findViewById(R.id.btn_search);
        tv_rowName = (TextView) findViewById(R.id.tv_row_name);
        tv_target[0] = (TextView) findViewById(R.id.tv_target1);
        tv_target[1] = (TextView) findViewById(R.id.tv_target2);
        tv_target[2] = (TextView) findViewById(R.id.tv_target3);
        tv_target[3] = (TextView) findViewById(R.id.tv_target4);
        tv_target[4] = (TextView) findViewById(R.id.tv_target5);
        btnOption[0] = (AppCompatButton) findViewById(R.id.btn_option1);
        btnOption[1] = (AppCompatButton) findViewById(R.id.btn_option2);
        btnOption[2] = (AppCompatButton) findViewById(R.id.btn_option3);
        btnOption[3] = (AppCompatButton) findViewById(R.id.btn_option4);
        btnOption[4] = (AppCompatButton) findViewById(R.id.btn_option5);
        btnOption[5] = (AppCompatButton) findViewById(R.id.btn_option6);
        btnOption[6] = (AppCompatButton) findViewById(R.id.btn_option7);
        btnOption[7] = (AppCompatButton) findViewById(R.id.btn_option8);
        btnOption[8] = (AppCompatButton) findViewById(R.id.btn_option9);
        btnOption[9] = (AppCompatButton) findViewById(R.id.btn_option10);

        btnOption[10] = (AppCompatButton) findViewById(R.id.btn_option11);
        btnOption[11] = (AppCompatButton) findViewById(R.id.btn_option12);
        btnOption[12] = (AppCompatButton) findViewById(R.id.btn_option13);
        btnOption[13] = (AppCompatButton) findViewById(R.id.btn_option14);
        btnOption[14] = (AppCompatButton) findViewById(R.id.btn_option15);
        btnOption[15] = (AppCompatButton) findViewById(R.id.btn_option16);
        btnOption[16] = (AppCompatButton) findViewById(R.id.btn_option17);
        btnOption[17] = (AppCompatButton) findViewById(R.id.btn_option18);
        btnOption[18] = (AppCompatButton) findViewById(R.id.btn_option19);
        btnOption[19] = (AppCompatButton) findViewById(R.id.btn_option20);

        btnOption[20] = (AppCompatButton) findViewById(R.id.btn_option21);
        btnOption[21] = (AppCompatButton) findViewById(R.id.btn_option22);
        btnOption[22] = (AppCompatButton) findViewById(R.id.btn_option23);
        btnOption[23] = (AppCompatButton) findViewById(R.id.btn_option24);
        btnOption[24] = (AppCompatButton) findViewById(R.id.btn_option25);
        btnOption[25] = (AppCompatButton) findViewById(R.id.btn_option26);
        btnOption[26] = (AppCompatButton) findViewById(R.id.btn_option27);
        btnOption[27] = (AppCompatButton) findViewById(R.id.btn_option28);
        btnOption[28] = (AppCompatButton) findViewById(R.id.btn_option29);
        btnOption[29] = (AppCompatButton) findViewById(R.id.btn_option30);

        btnOption[30] = (AppCompatButton) findViewById(R.id.btn_option31);
        btnOption[31] = (AppCompatButton) findViewById(R.id.btn_option32);
        btnOption[32] = (AppCompatButton) findViewById(R.id.btn_option33);
        btnOption[33] = (AppCompatButton) findViewById(R.id.btn_option34);
        btnOption[34] = (AppCompatButton) findViewById(R.id.btn_option35);
        btnOption[35] = (AppCompatButton) findViewById(R.id.btn_option36);
        btnOption[36] = (AppCompatButton) findViewById(R.id.btn_option37);
        btnOption[37] = (AppCompatButton) findViewById(R.id.btn_option38);
        btnOption[38] = (AppCompatButton) findViewById(R.id.btn_option39);
        btnOption[39] = (AppCompatButton) findViewById(R.id.btn_option40);

        btnOption[40] = (AppCompatButton) findViewById(R.id.btn_option41);
        btnOption[41] = (AppCompatButton) findViewById(R.id.btn_option42);
        btnOption[42] = (AppCompatButton) findViewById(R.id.btn_option43);
        btnOption[43] = (AppCompatButton) findViewById(R.id.btn_option44);
        btnOption[44] = (AppCompatButton) findViewById(R.id.btn_option45);
        btnOption[45] = (AppCompatButton) findViewById(R.id.btn_option46);

        arrayRowName[0] = "아";
        arrayRowName[1] = "카";
        arrayRowName[2] = "사";
        arrayRowName[3] = "카";
        arrayRowName[4] = "나";
        arrayRowName[5] = "하";
        arrayRowName[6] = "마";
        arrayRowName[7] = "와";
        arrayRowName[8] = "라";
        arrayRowName[9] = "야";
        initBtnOpt(); // 버튼 세팅
        setNewRow(rowIndex);
        setCorrectValue(correctIndex);
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