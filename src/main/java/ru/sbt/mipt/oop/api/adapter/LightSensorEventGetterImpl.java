package ru.sbt.mipt.oop.api.adapter;

import ru.sbt.mipt.oop.event_handlers.SensorEventType;

import java.util.Map;

public class LightSensorEventGetterImpl implements SensorEventGetter {
    Map typeGetter;

    public LightSensorEventGetterImpl(Map typeGetter) {
        this.typeGetter = typeGetter;
    }

    @Override
    public SensorEventType getType(String name) {
        return (SensorEventType) typeGetter.get(name);
    }
}
