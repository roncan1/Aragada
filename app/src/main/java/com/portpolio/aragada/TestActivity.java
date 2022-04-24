package com.portpolio.aragada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.file.Files;

public class TestActivity extends AppCompatActivity {

    ImageView btn_search, btn_backHome;
    TextView tv_rowName;
    TextView[] tv_target = new TextView[5];
    AppCompatButton[] option = new AppCompatButton[48];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        search_japanese();
        backHome();
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

    void init() {
        btn_backHome = (ImageView) findViewById(R.id.btn_backHome);
        btn_search = (ImageView) findViewById(R.id.btn_search);
        tv_rowName = (TextView) findViewById(R.id.tv_row_name);
        tv_target[0] = (TextView) findViewById(R.id.tv_target1);
        tv_target[1] = (TextView) findViewById(R.id.tv_target2);
        tv_target[2] = (TextView) findViewById(R.id.tv_target3);
        tv_target[3] = (TextView) findViewById(R.id.tv_target4);
        tv_target[4] = (TextView) findViewById(R.id.tv_target5);
        option[0] = (AppCompatButton) findViewById(R.id.btn_option1);
        option[1] = (AppCompatButton) findViewById(R.id.btn_option2);
        option[2] = (AppCompatButton) findViewById(R.id.btn_option3);
        option[3] = (AppCompatButton) findViewById(R.id.btn_option4);
        option[4] = (AppCompatButton) findViewById(R.id.btn_option5);
        option[5] = (AppCompatButton) findViewById(R.id.btn_option6);
        option[6] = (AppCompatButton) findViewById(R.id.btn_option7);
        option[7] = (AppCompatButton) findViewById(R.id.btn_option8);
        option[8] = (AppCompatButton) findViewById(R.id.btn_option9);
        option[9] = (AppCompatButton) findViewById(R.id.btn_option10);

        option[10] = (AppCompatButton) findViewById(R.id.btn_option11);
        option[11] = (AppCompatButton) findViewById(R.id.btn_option12);
        option[12] = (AppCompatButton) findViewById(R.id.btn_option13);
        option[13] = (AppCompatButton) findViewById(R.id.btn_option14);
        option[14] = (AppCompatButton) findViewById(R.id.btn_option15);
        option[15] = (AppCompatButton) findViewById(R.id.btn_option16);
        option[16] = (AppCompatButton) findViewById(R.id.btn_option17);
        option[17] = (AppCompatButton) findViewById(R.id.btn_option18);
        option[18] = (AppCompatButton) findViewById(R.id.btn_option19);
        option[19] = (AppCompatButton) findViewById(R.id.btn_option20);

        option[20] = (AppCompatButton) findViewById(R.id.btn_option21);
        option[21] = (AppCompatButton) findViewById(R.id.btn_option22);
        option[22] = (AppCompatButton) findViewById(R.id.btn_option23);
        option[23] = (AppCompatButton) findViewById(R.id.btn_option24);
        option[24] = (AppCompatButton) findViewById(R.id.btn_option25);
        option[25] = (AppCompatButton) findViewById(R.id.btn_option26);
        option[26] = (AppCompatButton) findViewById(R.id.btn_option27);
        option[27] = (AppCompatButton) findViewById(R.id.btn_option28);
        option[28] = (AppCompatButton) findViewById(R.id.btn_option29);
        option[29] = (AppCompatButton) findViewById(R.id.btn_option30);

        option[30] = (AppCompatButton) findViewById(R.id.btn_option31);
        option[31] = (AppCompatButton) findViewById(R.id.btn_option32);
        option[32] = (AppCompatButton) findViewById(R.id.btn_option33);
        option[33] = (AppCompatButton) findViewById(R.id.btn_option34);
        option[34] = (AppCompatButton) findViewById(R.id.btn_option35);
        option[35] = (AppCompatButton) findViewById(R.id.btn_option36);
        option[36] = (AppCompatButton) findViewById(R.id.btn_option37);
        option[37] = (AppCompatButton) findViewById(R.id.btn_option38);
        option[38] = (AppCompatButton) findViewById(R.id.btn_option39);
        option[39] = (AppCompatButton) findViewById(R.id.btn_option40);

        option[40] = (AppCompatButton) findViewById(R.id.btn_option41);
        option[41] = (AppCompatButton) findViewById(R.id.btn_option42);
        option[42] = (AppCompatButton) findViewById(R.id.btn_option43);
        option[43] = (AppCompatButton) findViewById(R.id.btn_option44);
        option[44] = (AppCompatButton) findViewById(R.id.btn_option45);
        option[45] = (AppCompatButton) findViewById(R.id.btn_option46);
        option[46] = (AppCompatButton) findViewById(R.id.btn_option47);
        option[47] = (AppCompatButton) findViewById(R.id.btn_option48);
    }
}