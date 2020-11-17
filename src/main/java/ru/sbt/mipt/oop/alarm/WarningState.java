package ru.sbt.mipt.oop.alarm;

public class WarningState implements AlarmState {

    private final Alarm alarm;

    public WarningState(Alarm alarm) { this.alarm = alarm; }

    @Override
    public void activate(int code) {}

    @Override
    public void deactivate(int code) {
        if (alarm.getCode() == code) {
            System.out.println("Smart Alarm is on!");
            alarm.changeState(new DeactivatedState(alarm));
        }
    }

    @Override
    public void alarm() {}
}
