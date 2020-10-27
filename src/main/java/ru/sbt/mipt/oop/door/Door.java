package ru.sbt.mipt.oop.door;

import org.springframework.stereotype.Component;

@Component
public class Door {
    private final String id;
    private boolean isOpen;
    private boolean isLocked;

    public Door(boolean isOpen, String id, boolean isLocked) {
        this.isOpen = isOpen;
        this.id = id;
        this.isLocked = isLocked;
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
}
