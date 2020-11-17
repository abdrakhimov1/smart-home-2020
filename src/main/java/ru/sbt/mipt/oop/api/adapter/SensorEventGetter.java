package ru.sbt.mipt.oop.api.adapter;

import ru.sbt.mipt.oop.event_handlers.SensorEventType;

public interface SensorEventGetter {
    SensorEventType getType(String name);
}
