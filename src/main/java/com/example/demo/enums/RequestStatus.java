package com.example.demo.enums;

public enum RequestStatus {
    OPEN("Открыто"),
    COMPLETED("Выполнено"),
    NOT_COMPLETED("Не выполнено");

    private final String longName;

    RequestStatus(String longName) {
        this.longName = longName;
    }

    public String getLongName(){
        return longName;
    }
}
