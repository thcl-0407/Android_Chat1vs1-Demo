package com.example.demochat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demochat.Adapter.MessageAdapter;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Socket client_socket;
    Button btnGui;
    EditText etTinNhan;

    RecyclerView rvTinNhan;

    ArrayList<Message> TinNhans;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TinNhans = new ArrayList<>();

        rvTinNhan = (RecyclerView) findViewById(R.id.rvTinNhan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setStackFromEnd(true);
        messageAdapter = new MessageAdapter(MainActivity.this, TinNhans);
        rvTinNhan.setAdapter(messageAdapter);
        rvTinNhan.setLayoutManager(layoutManager);

        String TenPhong = getIntent().getStringExtra("TenPhong");
        String NickName = getIntent().getStringExtra("NickName");

        JSONObject object = new JSONObject();
        try {
            object.put("NickName", NickName);
            object.put("TenPhong", TenPhong);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            client_socket = IO.socket("http://192.168.4.177:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        client_socket.connect();


        client_socket.on("SERVER_GUI_TIN_NHAN", NhanTinNhanServer);
        client_socket.emit("DANG_KY_PHONG", object);

        etTinNhan = (EditText) findViewById(R.id.etTinNhan);
        btnGui = (Button) findViewById(R.id.btnGui);
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinNhans.add(new Message(etTinNhan.getText().toString().trim(), ""));
                client_socket.emit("CLIENT_GUI_TIN_NHAN", etTinNhan.getText().toString().trim());
                messageAdapter.notifyDataSetChanged();
                rvTinNhan.scrollToPosition(TinNhans.size() - 1);
            }
        });
    }

    private Emitter.Listener NhanTinNhanServer = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object = (JSONObject) args[0];
                    String TinNhan_Server = "";

                    try {
                        TinNhan_Server = object.getString("TinNhan");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    TinNhans.add(new Message("", TinNhan_Server));
                    messageAdapter.notifyDataSetChanged();
                    rvTinNhan.scrollToPosition(TinNhans.size() - 1);
                }
            });
        }
    };
}