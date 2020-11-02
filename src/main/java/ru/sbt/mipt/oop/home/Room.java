package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.event_handlers.DoorEventHandler;
import ru.sbt.mipt.oop.event_handlers.LightEventHandler;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;
import ru.sbt.mipt.oop.light.Light;

import java.util.Collection;

public class Room implements Actionable{

    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {

        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
        action.accept(this);
    }
}
