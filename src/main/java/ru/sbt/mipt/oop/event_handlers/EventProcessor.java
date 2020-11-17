package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class EventProcessor {

    final SmartHome smartHome;
    final EventSolver eventSolver;
    final EventGenerator eventGenerator;
    final SensorEvent event;
    final EventSolver eventSolverDecorator;

    public EventProcessor(SmartHome smartHome, EventGenerator eventGenerator, EventSolver eventSolver, EventSolver eventSolverDecorator) {
        this.eventGenerator = eventGenerator;
        this.smartHome = smartHome;
        this.eventSolver = eventSolver;
        this.event = eventGenerator.makeEvent();
        this.eventSolverDecorator = eventSolverDecorator;
    }

    public void processEvent(){
        SensorEvent event = eventGenerator.makeEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventSolverDecorator.solveEvent(smartHome, event);
            event = eventGenerator.makeEvent();
        }
    }
}

