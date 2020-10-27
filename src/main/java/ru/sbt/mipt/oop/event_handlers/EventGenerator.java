package ru.sbt.mipt.oop.event_handlers;

import org.springframework.stereotype.Component;

@Component
public class EventGenerator {

    SensorEvent event = getNextSensorEvent();

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

    public SensorEvent makeEvent() {
        return getNextSensorEvent();
    }
}
