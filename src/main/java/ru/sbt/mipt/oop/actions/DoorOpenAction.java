package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.door.Door;


public class DoorOpenAction implements Action {
    private final String doorObjectID;

    public DoorOpenAction(String doorObjectID) {
        this.doorObjectID = doorObjectID;
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Door) {
            if (((Door) actionable).getId().equals(doorObjectID)) {
                ((Door) actionable).setOpen(true);
                System.out.println("Door " + doorObjectID + " was opened.");
            }
        }
    }
}
