package ru.sbt.mipt.oop.warning_senders;


public class SmsWarningSender implements WarningSender {
    @Override
    public void sendWarning() {
        System.out.println("SMS: ALARM!!");
    }
}
