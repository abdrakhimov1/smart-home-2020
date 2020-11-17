package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

public class DoorClosedAction implements Action {
    private final String doorObjectID;
    private SmartHome smartHome;

    public DoorClosedAction(String id, SmartHome smartHome) {
        this.doorObjectID = id;
        this.smartHome = smartHome;
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Door) {
            if (((Door) actionable).getId().equals(doorObjectID)) {
                ((Door) actionable).setOpen(false);
                System.out.println("Door " + doorObjectID + " was closed.");
                smartHome.execute(x -> {
                    if (x instanceof Room) {
                        if (((Room) x).getName().equals("hall")) {
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
