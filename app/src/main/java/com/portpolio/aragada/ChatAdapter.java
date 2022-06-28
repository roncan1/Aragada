package com.portpolio.aragada;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ChatAdapter extends BaseAdapter {
    Context context = null;
    LayoutInflater layoutInflater = null;
    ArrayList<ChatData> chatData;
    TextToSpeech tts;

    public ChatAdapter(Context context, ArrayList<ChatData> data) {
        this.context = context;
        this.chatData = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return chatData.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return chatData.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View daaraChatView = layoutInflater.inflate(R.layout.chat_daara, null);
        View userChatView = layoutInflater.inflate(R.layout.chat_user, null);
        TextView tv_daara = daaraChatView.findViewById(R.id.tv_daara);
        TextView tv_user = userChatView.findViewById(R.id.tv_user);
        ImageButton btn_tts = daaraChatView.findViewById(R.id.btn_chat_tts);
        ImageButton btn_translate = daaraChatView.findViewById(R.id.translate);

        setTts();

        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        String word = tv_daara.getText().toString();
                        PapagoTranslate papagoTranslate = new PapagoTranslate();
                        String resultWord;
                        resultWord = papagoTranslate.getTranslation(word, "ja", "ko");
                        tv_daara.setText(resultWord);
                    }
                }.start();
            }
        });

        btn_tts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(tv_daara.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                Log.d("터치됨", "onClick: " + tv_daara.getText().toString());
            }
        });

        if (i % 2 != 0) {
            tv_daara.setText(chatData.get(i).getValue());
            return daaraChatView;
        } else {
            tv_user.setText(chatData.get(i).getValue());
            return userChatView;
        }

    }



    void setTts() {
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.JAPANESE);
                }
            }
        });
    }
}
