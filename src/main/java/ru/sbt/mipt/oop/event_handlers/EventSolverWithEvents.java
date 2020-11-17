package ru.sbt.mipt.oop.event_handlers;

import java.util.List;

public interface EventSolverWithEvents extends EventSolver {
    List<GeneralEvent> getEvents();
}
