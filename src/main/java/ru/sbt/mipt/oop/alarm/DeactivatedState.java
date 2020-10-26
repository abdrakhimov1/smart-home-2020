package ru.sbt.mipt.oop.alarm;

public class DeactivatedState implements AlarmState {

    Alarm alarm;

    public DeactivatedState(Alarm alarm) { this.alarm = alarm; }

    @Override
    public void activate(int code) {
        System.out.println("Smart Alarm is on!");
        alarm.changeState(new ActivateStateImplementation(alarm));
    }

    @Override
    public void deactivate(int code) {}

    @Override
    public void alarm() {
        alarm.changeState(new WarningState(alarm));
    }
}