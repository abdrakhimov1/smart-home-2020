package ru.sbt.mipt.oop.event_handlers;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;

@Component
public class EventSolverImplementation implements EventSolverWithEvents{
    ArrayList<GeneralEvent> events;
    LightEventHandler lightEventHandler = new LightEventHandler();
    DoorEventHandler doorEventHandler = new DoorEventHandler();

    public ArrayList<GeneralEvent> getEvents() {
        return events;
    }

    public EventSolverImplementation() {
        this.events  = new ArrayList<>();
        events.add(lightEventHandler);
        events.add(doorEventHandler);
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        for (GeneralEvent eventToHandle : events) {
            eventToHandle.handleEvent(event, smartHome);
        }
    }
}
