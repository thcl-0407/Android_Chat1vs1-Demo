package com.example.demochat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DangKyChatActivity extends AppCompatActivity {

    Button btnBatDauChat;
    EditText etTaoPhong;
    EditText etNickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_chat);

        btnBatDauChat = (Button) findViewById(R.id.btnBatDauChat);
        etTaoPhong = (EditText) findViewById(R.id.etTenRoom);
        etNickName = (EditText) findViewById(R.id.etNickName);

        btnBatDauChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyChatActivity.this, MainActivity.class);
                intent.putExtra("TenPhong", etTaoPhong.getText().toString());
                intent.putExtra("NickName", etNickName.getText().toString());

                startActivity(intent);
                finish();
            }
        });
    }
}