package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.actions.LightOnAction;
import ru.sbt.mipt.oop.home.SmartHome;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.event_handlers.SensorEventType.LIGHT_ON;

public class LightEventHandler implements GeneralEvent{

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_ON){
            LightOnAction lightOnAction = new LightOnAction(event.getObjectId());
            smartHome.execute(lightOnAction);
        }
        if (event.getType() == LIGHT_OFF){
            LightOffAction lightOffAction = new LightOffAction(event.getObjectId());
            smartHome.execute(lightOffAction);
        }
    }
}