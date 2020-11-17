package ru.sbt.mipt.oop.alarm;

public class ActivateState implements AlarmState {

    private final Alarm alarm;

    public ActivateState(Alarm alarm) { this.alarm = alarm; }

    @Override
    public void activate(int code) {}

    @Override
    public void deactivate(int code) {

        if (alarm.getCode() == code) {
            System.out.println("Alarm is off!");
            alarm.changeState(new DeactivatedState(alarm));
        } else {
            System.out.println("ALARM!");
            alarm.changeState(new WarningState(alarm));
        }
    }

    @Override
    public void alarm() {
        System.out.println("ALARM!");
        alarm.changeState(new WarningState(alarm));
    }
}
