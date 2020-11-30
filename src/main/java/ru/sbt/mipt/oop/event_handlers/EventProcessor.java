package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

import java.util.Iterator;

public class EventProcessor {

    final SmartHome smartHome;
    final EventSolver eventSolver;
    final EventGenerator eventGenerator;
    final SensorEvent event;
    final GeneralEvent eventSolverDecorator;



    public EventProcessor(SmartHome smartHome, EventGenerator eventGenerator, EventSolver eventSolver, GeneralEvent eventSolverDecorator) {
        this.eventGenerator = eventGenerator;
        this.smartHome = smartHome;
        this.eventSolver = eventSolver;
        this.event = eventGenerator.makeEvent();
        this.eventSolverDecorator = eventSolverDecorator;
    }

    public void processEvent(){
        EventGenerator eventGenerator = new EventGenerator();
        SensorEvent event = eventGenerator.makeEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventSolverDecorator.handleEvent(event, smartHome);
            event = eventGenerator.makeEvent();
        }
    }
}

