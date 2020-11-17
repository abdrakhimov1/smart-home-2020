package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.rc.Command;
import ru.sbt.mipt.oop.rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlI implements RemoteControl {

    private Map<String, Command> remoteControlPanel = new HashMap<>();

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (remoteControlPanel.containsKey(buttonCode)) {
            remoteControlPanel.get(buttonCode).execute();
        }
    }

    public void setCommand(String buttonCode, Command command) {
        if (remoteControlPanel.containsKey(buttonCode)) {
            remoteControlPanel.replace(buttonCode, command);
        } else {
            remoteControlPanel.put(buttonCode, command);
        }
    }
}
