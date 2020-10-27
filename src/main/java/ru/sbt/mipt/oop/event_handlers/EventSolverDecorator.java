package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.alarm.ActivateStateImplementation;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.WarningState;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class EventSolverDecorator implements EventSolver {

    private EventSolverWithEvents wrapper;
    private final Alarm alarm;
    private AlarmEventHandler alarmEventHandler;


    EventSolverDecorator(EventSolverWithEvents eventSolver, AlarmEventHandler alarmEventHandler, SmartHome smartHome) {
        this.alarm = smartHome.getAlarm();
        this.alarmEventHandler = alarmEventHandler;
        this.wrapper = eventSolver;
    }


    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        if (alarm.getState() instanceof ActivateStateImplementation) {
            alarm.alarm();
        }
        if (alarm.getState() instanceof WarningState) {
            System.out.println("SMS: ALARM!!");
            return;
        }
        for (GeneralEvent event1 : wrapper.getEvents()) {
            event1.handleEvent(event, smartHome);
        }
    }
}
