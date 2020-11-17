package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public interface GeneralEvent {
    void handleEvent(SensorEvent event, SmartHome smartHome);
}
