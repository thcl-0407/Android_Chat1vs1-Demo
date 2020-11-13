package com.example.demochat;

public class Message {
    private String TinGui;
    private String TinNhan;

    public Message(String tinGui, String tinNhan) {
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
}
