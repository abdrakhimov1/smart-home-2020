package ru.sbt.mipt.oop.api.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.event_handlers.EventSolver;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;
import ru.sbt.mipt.oop.event_handlers.SensorEventType;
import ru.sbt.mipt.oop.home.SmartHome;


import java.util.ArrayList;
import java.util.Collection;

@Component
public class Adapter implements com.coolcompany.smarthome.events.EventHandler {

    private final EventSolver eventSolver;
    private SmartHome smartHome;
    private Collection<SensorEventGetter> sensorEventGetters;

    public Adapter(EventSolver eventSolver, SmartHome smartHome,  Collection<SensorEventGetter> sensorEventGetters) {
        this.eventSolver = eventSolver;
        this.smartHome = smartHome;
        this.sensorEventGetters = sensorEventGetters;
    }


    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEventType myEventType = adaptate(event.getEventType());
        SensorEvent myEvent = new SensorEvent(myEventType, event.getObjectId());
        eventSolver.solveEvent(smartHome, myEvent);
    }

    private SensorEventType adaptate(String eventType) {
        for (var eventGetter : sensorEventGetters) {
            var sensorEventType = eventGetter.getType(eventType);
            if (sensorEventType != null) {
                return sensorEventType;
            }
        }
        return null;
    }
}
