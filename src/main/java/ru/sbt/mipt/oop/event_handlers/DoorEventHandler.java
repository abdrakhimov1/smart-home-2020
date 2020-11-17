package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.actions.DoorClosedAction;
import ru.sbt.mipt.oop.actions.DoorOpenAction;
import ru.sbt.mipt.oop.home.SmartHome;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements GeneralEvent{

    public DoorEventHandler() {
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN) {
            DoorOpenAction doorOpenAction = new DoorOpenAction(event.getObjectId());
            smartHome.execute(doorOpenAction);
        }
        if (event.getType() == DOOR_CLOSED){
            DoorClosedAction doorClosedAction = new DoorClosedAction(event.getObjectId(), smartHome);
            smartHome.execute(doorClosedAction);
        }
    }
}

