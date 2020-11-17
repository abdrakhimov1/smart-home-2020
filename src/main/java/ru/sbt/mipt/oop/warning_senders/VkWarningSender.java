package ru.sbt.mipt.oop.warning_senders;

public class VkWarningSender implements WarningSender {
    @Override
    public void sendWarning() {
        System.out.println("VK: ALARM!!");
    }
}
