package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.home.SmartHome;

public class ActivateWarningState implements Command {

    private SmartHome smartHome;

    @Override
    public void execute() {
        smartHome.getAlarm().alarm();
    }
}
