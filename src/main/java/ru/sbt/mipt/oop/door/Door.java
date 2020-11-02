package ru.sbt.mipt.oop.door;

import ru.sbt.mipt.oop.home.Action;
import ru.sbt.mipt.oop.home.Actionable;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }
}
