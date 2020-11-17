package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.light.Light;

import java.util.Collection;

public class Room implements Actionable {

    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
        for (Actionable actionable : doors
             ) {
            actionable.execute(action);
        }
        for (Actionable actionable : lights
             ) {
            actionable.execute(action);
        }
    }
}