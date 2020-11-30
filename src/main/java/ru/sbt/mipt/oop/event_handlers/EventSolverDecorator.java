package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.WarningState;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.List;

public class EventSolverDecorator implements GeneralEvent {


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
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
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