package com.example.demochat;

public class Message {
    private String NickName;
    private String TinGui;
    private String TinNhan;

    public Message(String nickName, String tinGui, String tinNhan) {
        NickName = nickName;
        TinGui = tinGui;
        TinNhan = tinNhan;
    }

    public String getTinGui() {
        return TinGui;
    }

    public void setTinGui(String tinGui) {
        TinGui = tinGui;
    }

    public String getTinNhan() {
        return TinNhan;
    }

    public void setTinNhan(String tinNhan) {
        TinNhan = tinNhan;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }
}
