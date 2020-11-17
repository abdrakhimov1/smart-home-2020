package ru.sbt.mipt.oop.event_handlers;
import org.springframework.beans.factory.annotation.Autowired;


import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;

public class EventSolverImplementation implements EventSolverWithEvents{
    ArrayList<GeneralEvent> events;


    public ArrayList<GeneralEvent> getEvents() {
        return events;
    }


    public EventSolverImplementation(GeneralEvent lightEventHandler, GeneralEvent doorEventHandler) {
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
