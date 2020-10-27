package ru.sbt.mipt.oop.alarm;

import org.springframework.stereotype.Component;

@Component
public class Alarm {

    private AlarmState state;
    private int code;

    public Alarm(int code) {
        this.state = new DeactivatedState(this);
        this.code = code;
    }

    public void changeState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {
        return state;
    }

    int getCode() {
        return code;
    }

    public void activate(int code){
        state.activate(code);
    }

    public void deactivate(int code){
        state.deactivate(code);
    }

    public void alarm(){
        state.alarm();
    }
}
