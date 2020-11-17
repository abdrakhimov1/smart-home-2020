package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;
import ru.sbt.mipt.oop.event_handlers.SensorEventType;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

public class DoorAction implements Action {

    private final String doorObjectID;
    private final SensorEvent event;
    private final SmartHome smartHome;

    public DoorAction(SensorEvent event, SmartHome smartHome) {
        this.smartHome = smartHome;
        this.event = event;
        this.doorObjectID = event.getObjectId();
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Door) {
            if (event.getType() == SensorEventType.DOOR_OPEN) {
                if (((Door) actionable).getId().equals(doorObjectID)) {
                    ((Door) actionable).setOpen(true);
                    System.out.println("Door " + doorObjectID + " was opened.");
                }
            }
            if (event.getType() == SensorEventType.DOOR_CLOSED) {
                if (((Door) actionable).getId().equals(doorObjectID)) {
                    ((Door) actionable).setOpen(false);
                    System.out.println("Door " + doorObjectID + " was closed.");
                    smartHome.execute(x -> {
                        if (x instanceof Room) {
                            if (((Room) x).getName() == "hall") {
                                smartHome.execute(t -> {
                                    if (t instanceof Light) {
                                        Light light = (Light) t;
                                        light.setOn(false);
                                        System.out.println("Light " + light.getId() + " was off.");
                                    }
                                });
                            }
                        }
                    });
                }
            }
        }
    }
}
