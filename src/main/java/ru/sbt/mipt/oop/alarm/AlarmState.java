package ru.sbt.mipt.oop.alarm;

public interface AlarmState {
    void activate(int code);
    void deactivate(int code);
    void alarm();
}
