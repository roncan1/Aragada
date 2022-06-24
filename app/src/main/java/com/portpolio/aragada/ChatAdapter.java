package com.portpolio.aragada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    Context context = null;
    LayoutInflater layoutInflater = null;
    ArrayList<ChatData> chatData;

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

        if (i % 2 != 0) {
            tv_daara.setText(chatData.get(i).getValue());
            return daaraChatView;
        } else {
            tv_user.setText(chatData.get(i).getValue());
            return userChatView;
        }

    }
}
