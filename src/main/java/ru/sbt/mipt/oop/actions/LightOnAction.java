package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.event_handlers.EventSolverImplementation;
import ru.sbt.mipt.oop.light.Light;

public class LightOnAction implements Action {

    private final String lightObjectID;

    public LightOnAction(String lightObjectID) {
        this.lightObjectID = lightObjectID;
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Light) {
            if (((Light) actionable).getId().equals(lightObjectID)) {
                ((Light) actionable).setOn(true);
                System.out.println("Light " + lightObjectID + " was turned on.");
            }
        }
    }
}
