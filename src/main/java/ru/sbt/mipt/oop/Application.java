package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.condition.HomeConditionImplementation;
import ru.sbt.mipt.oop.event_handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.rc.Keys;
import ru.sbt.mipt.oop.rc.UserRemoteController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeConditionImplementation homeConditionImplementation = new HomeConditionImplementation();
        SmartHome smartHome = homeConditionImplementation.smartHomeCondition();
        smartHome.setAlarm();
        // начинаем цикл обработки событий
        List<GeneralEvent> eventHandlersList = Arrays.asList(new LightEventHandler(), new DoorEventHandler(), new ClosedHallDoorEventHandler(), new AlarmEventHandler());
        EventProcessor eventProcessor = new EventProcessor(smartHome, new EventGenerator(), new EventSolverImplementation(eventHandlersList), new EventSolverDecorator(eventHandlersList, smartHome));
        eventProcessor.processEvent();
    }
}
