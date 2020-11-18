package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.rc.Command;
import ru.sbt.mipt.oop.rc.RemoteControl;

import java.util.Map;

public class RemoteControlImpl implements RemoteControl {

    private final Map<String, Command> remoteControlPanel;

    public RemoteControlImpl(Map<String, Command> remoteControlPanel) {
        this.remoteControlPanel = remoteControlPanel;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (remoteControlPanel.containsKey(buttonCode)) {
            remoteControlPanel.get(buttonCode).execute();
        }
    }
}
