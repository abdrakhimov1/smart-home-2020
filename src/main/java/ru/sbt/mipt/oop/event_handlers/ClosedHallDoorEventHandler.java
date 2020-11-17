package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.actions.DoorClosedAction;
import ru.sbt.mipt.oop.home.SmartHome;

public class ClosedHallDoorEventHandler implements GeneralEvent{

    public ClosedHallDoorEventHandler() {
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        DoorClosedAction doorClosedAction = new DoorClosedAction(event.getObjectId(), smartHome);
        smartHome.execute(doorClosedAction);
    }
}
