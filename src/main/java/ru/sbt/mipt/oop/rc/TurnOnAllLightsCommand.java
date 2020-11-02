package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

public class TurnOnAllLightsCommand implements Command {

    private SmartHome smartHome;

    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeInstance -> {
            if(homeInstance instanceof Light){
                Light light = (Light) homeInstance;
                light.setOn(true);
            }
        });
    }
}
