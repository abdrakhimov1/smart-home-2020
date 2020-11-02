package ru.sbt.mipt.oop.light;

import ru.sbt.mipt.oop.home.Action;
import ru.sbt.mipt.oop.home.Actionable;

public class Light implements Actionable {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }
}
