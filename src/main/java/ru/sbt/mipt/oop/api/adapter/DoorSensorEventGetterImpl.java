package ru.sbt.mipt.oop.api.adapter;

import ru.sbt.mipt.oop.event_handlers.SensorEventType;

import java.util.Map;

public class DoorSensorEventGetterImpl implements SensorEventGetter {
    private Map<String, SensorEventType> typeGetter;
    public DoorSensorEventGetterImpl(Map typeGetter) {
        this.typeGetter = typeGetter;
    }

    @Override
    public SensorEventType getType(String name) {
        return (SensorEventType) typeGetter.get(name);
    }
}
