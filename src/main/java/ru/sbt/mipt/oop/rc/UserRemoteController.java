package ru.sbt.mipt.oop.rc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserRemoteController implements RemoteControl{

    private final String id;
    private HashMap<String, Command> keysCommands = new HashMap<>();

    public UserRemoteController(String id, HashMap commands){
        this.id = id;
        this.keysCommands = commands;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        try {
            keysCommands.get(buttonCode).execute();
        } catch (IllegalArgumentException e) {
            System.out.println("You didnt set any command on this button.");
        }
    }
}
