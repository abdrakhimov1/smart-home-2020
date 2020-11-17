package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class EventProcessor {

    SmartHome smartHome;
    EventSolver eventSolver;
    EventGenerator eventGenerator;
    EventSolverDecorator eventSolverDecorator;



    public EventProcessor(SmartHome smartHome, EventSolverWithEvents eventSolver, EventGenerator eventGenerator) {
        this.eventSolver = eventSolver;
        this.eventGenerator = eventGenerator;
        this.smartHome = smartHome;
        this.eventSolverDecorator = new EventSolverDecorator(eventSolver, new AlarmEventHandler(smartHome), smartHome);

    }

    public void processEvent(){
        EventGenerator eventGenerator = new EventGenerator();
        SensorEvent event = eventGenerator.makeEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventSolverDecorator.solveEvent(smartHome, event);
            event = eventGenerator.makeEvent();
        }
    }
}

