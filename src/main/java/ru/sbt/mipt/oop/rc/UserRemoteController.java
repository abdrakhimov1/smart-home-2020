package ru.sbt.mipt.oop.rc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserRemoteController implements RemoteControl{

    private final String id;
    private final HashMap<String, Command> keysCommandHashMap = new HashMap<>();

    public UserRemoteController(List<Keys> keys, String id){
        var list = List.of("A", "B", "C", "D", "1", "2", "3", "4");
        this.id = id;
        for (String key : list) {
            keysCommandHashMap.put(key, null);
        }
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        try {
            keysCommandHashMap.get(buttonCode).execute();
        } catch (IllegalArgumentException e) {
            System.out.println("You didnt set any command on this button.");
        }
    }

    public void setCommandToKey(String key, Command command){
        keysCommandHashMap.replace(key, command);
    }
}
