package com.silverwolf.chattwofragment.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silverwolf.chattwofragment.Activity.MainActivity;
import com.silverwolf.chattwofragment.R;

import java.util.ArrayList;

/**
 * Created by SilverWolf on 04/04/2016.
 */
public class User2Fragment extends Fragment {

    private TextView tvChatContent;
    private EditText edtChat;
    private Button btnSwitch, btnSend;
    public ArrayList<String> strings;

    OnSwitchListener mCallback;

    public interface OnSwitchListener {
        public void sendTextTo1(ArrayList<String> text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnSwitchListener) context;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user2, container, false);
        strings = new ArrayList<>();
        tvChatContent = (TextView) rootView.findViewById(R.id.tvChatContent);
        edtChat = (EditText) rootView.findViewById(R.id.edtChat);
        btnSwitch = (Button) rootView.findViewById(R.id.btnSwitch);
        btnSend = (Button) rootView.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edtChat.getText())) {    //Check textview is empty?
                    strings.add(MainActivity.user2 + edtChat.getText().toString());
                    addText(MainActivity.user2 + edtChat.getText().toString());
                    edtChat.setText("");
                }
            }
        });

        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText(strings);
            }
        });

        getText();

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    //set content to user1
    public void sendText(ArrayList<String> text){
        mCallback.sendTextTo1(text);
    }

    //get content chat
    public void getText(){
        if (getArguments()!=null){
            strings = getArguments().getStringArrayList(MainActivity.valueKey);
            setText(strings);
        }
    }

    //set content to textview
    private void setText(ArrayList<String> strings){
        for (int i=0; i<strings.size(); i++){
            tvChatContent.append(strings.get(i) + "\n");
        }
    }

    //add content to textview
    private void addText(String string){
        tvChatContent.append(string + "\n");
    }
}
