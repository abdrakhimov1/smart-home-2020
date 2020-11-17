package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;
import ru.sbt.mipt.oop.event_handlers.SensorEventType;
import ru.sbt.mipt.oop.light.Light;

public class LightAction implements Action {

    private final String lightObjectID;
    private SensorEvent event;

    public LightAction( SensorEvent event) {
        this.event = event;
        lightObjectID = event.getObjectId();
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Light) {
            if (event.getType() == SensorEventType.LIGHT_ON) {
                if (((Light) actionable).getId().equals(lightObjectID)) {
                    ((Light) actionable).setOn(true);
                    System.out.println("Light " + lightObjectID + " was turned on.");
                }
            }
            if (event.getType() == SensorEventType.LIGHT_OFF){
                if (((Light) actionable).getId().equals(lightObjectID)) {
                    ((Light) actionable).setOn(false);
                    System.out.println("Light " + lightObjectID + " was turned off.");
                }
            }
        }
    }
}
