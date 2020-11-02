package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.home.SmartHome;

public class ActivateAlarmCommand implements Command {

    private int activateCode;
    private SmartHome smartHome;

    public ActivateAlarmCommand(int activateCode, SmartHome smartHome) {
        this.activateCode = activateCode;
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(activateCode);
    }
}
