package com.example.demo.enums;

public enum UserStatus {
    ONLINE("В сети"),
    OFFLINE("Не в сети");

    private final String longName;

    UserStatus(String longName) {
        this.longName = longName;
    }

    public String getLongName(){
        return longName;
    }
}
