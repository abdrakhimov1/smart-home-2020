package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.WarningState;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.List;
import java.util.Map;

public class EventSolverDecorator implements EventSolver {



    private final List<GeneralEvent> eventHandlersList;



    public EventSolverDecorator(List<GeneralEvent> eventHandlersList) {

        this.eventHandlersList = eventHandlersList;


    }

    private boolean isAlarm(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_DEACTIVATE || event.getType() == SensorEventType.ALARM_ACTIVATE;
    }

    private void standardAccess(SensorEvent event, SmartHome smartHome) {
        eventHandlersList.forEach(handler -> handler.handleEvent(event, smartHome));
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        Alarm alarm = smartHome.getAlarm();
        if (alarm == null || isAlarm(event)) {
            standardAccess(event, smartHome);
            return;
        }
        if (alarm.isAlarmed()) alarm.changeState(new WarningState(alarm));
        if (alarm.isWarning()) {
            System.out.println("Sending sms");
            return;
        }
    }

}