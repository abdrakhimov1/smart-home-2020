package ru.sbt.mipt.oop.api.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;
import ru.sbt.mipt.oop.event_handlers.SensorEventType;

import javax.annotation.PostConstruct;
import java.util.Map;

public class DoorSensorEventGetterImpl implements SensorEventGetter {
    Map typeGetter;
    public DoorSensorEventGetterImpl(Map typeGetter) {
        this.typeGetter = typeGetter;
    }

    @Override
    public SensorEventType getType(String name) {
        return (SensorEventType) typeGetter.get(name);
    }
}
