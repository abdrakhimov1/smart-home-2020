package ru.sbt.mipt.oop.warning_senders;

public class WarningSolver {
    private final String type;

    public WarningSolver(String type) {
        this.type = type;
    }

    public void solveWarning(){
        WarningSender sender;
        if (type.equals("vk")){
            sender = new VkWarningSender();
        } else {
            sender = new SmsWarningSender();
        }
        sender.sendWarning();
    }
}
