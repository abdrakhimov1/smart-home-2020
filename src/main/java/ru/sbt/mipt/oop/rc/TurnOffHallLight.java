package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

public class TurnOffHallLight implements Command {

    private final SmartHome smartHome;

    public TurnOffHallLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeInstance -> {
            if (homeInstance instanceof Room){
                Room room = (Room) homeInstance;
                if (room.getName().equals("hall")){
                    room.execute(roomInstance -> {
                        if (roomInstance instanceof Light){
                            Light light = (Light) roomInstance;
                            light.setOn(false);
                        }
                    });
                }
            }
        });
    }
}