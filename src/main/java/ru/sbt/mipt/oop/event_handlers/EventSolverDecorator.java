package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.List;
import java.util.Map;

public class EventSolverDecorator implements EventSolver {

    private final Alarm alarm;

    private final List<GeneralEvent> eventHandlersList;
    private EventSolver eventSolver;
    private final Map<String, SensorEventType> adaptedEventType;


    public EventSolverDecorator(List<GeneralEvent> eventHandlersList, Alarm alarm, EventSolver eventSolver, Map<String, SensorEventType> sensorEventTypes) {
        this.alarm = alarm;
        this.eventHandlersList = eventHandlersList;
        this.eventSolver = eventSolver;
        this.adaptedEventType = sensorEventTypes;
    }

    private SensorEventType decorate(SensorEventType eventType) {
        return adaptedEventType.get(eventType);
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        for (GeneralEvent handler : eventHandlersList){
            SensorEventType myEventType = decorate(event.getType());
            if (myEventType == null) return;
            SensorEvent newEvent = new SensorEvent(myEventType, event.getObjectId());
            handler.handleEvent(newEvent, smartHome);
        }
    }
}