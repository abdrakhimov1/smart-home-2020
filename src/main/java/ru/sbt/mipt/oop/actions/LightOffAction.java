package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.light.Light;

public class LightOffAction implements Action {
    private final String lightObjectID;

    public LightOffAction(String lightObjectID) {
        this.lightObjectID = lightObjectID;
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Light) {
            if (((Light) actionable).getId().equals(lightObjectID)) {
                ((Light) actionable).setOn(false);
                System.out.println("Light " + lightObjectID + " was turned off.");
            }
        }
    }
}
