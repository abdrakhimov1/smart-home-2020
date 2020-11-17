package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public interface EventSolver {
    void solveEvent(SmartHome smartHome, SensorEvent event);
}
