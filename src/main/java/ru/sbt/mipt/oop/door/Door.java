package ru.sbt.mipt.oop.door;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;
    private boolean isLocked;

    public Door(boolean isOpen, String id, boolean isLocked) {
        this.isOpen = isOpen;
        this.id = id;
        this.isLocked = isLocked;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
    public void setLocked(boolean locked){
        isLocked = locked;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }
}
