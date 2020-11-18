package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.warning_senders.WarningSolver;

import java.util.ArrayList;
import java.util.List;

public class EventSolverDecorator implements EventSolver {

    private final Alarm alarm;

    private final List<GeneralEvent> eventHandlersList;


    public EventSolverDecorator(List<GeneralEvent> eventHandlersList, Alarm alarm) {
        this.alarm = alarm;
        this.eventHandlersList = eventHandlersList;
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {

        if (alarm.isAlarmed()) {
            alarm.alarm();
        }
        if (alarm.isWarning()) {
            WarningSolver solver = new WarningSolver("sms");
            solver.solveWarning();
            return;
        }
        for (GeneralEvent event1 : eventHandlersList) {
            event1.handleEvent(event, smartHome);
        }
    }
}