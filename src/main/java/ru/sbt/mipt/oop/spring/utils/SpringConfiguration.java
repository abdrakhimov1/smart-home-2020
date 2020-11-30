package ru.sbt.mipt.oop.spring.utils;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.api.adapter.Adapter;
import ru.sbt.mipt.oop.api.adapter.DoorSensorEventGetterImpl;
import ru.sbt.mipt.oop.api.adapter.LightSensorEventGetterImpl;
import ru.sbt.mipt.oop.api.adapter.SensorEventGetter;
import ru.sbt.mipt.oop.condition.HomeConditionImplementation;
import ru.sbt.mipt.oop.event_handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.*;

@Configuration
public class SpringConfiguration {

    @Bean
    EventSolverWithEvents eventSolver(){
        return new EventSolverImplementation(lightEventHandler(), doorEventHandler());
    }

    @Bean
    Adapter eventSolverImplementationAdapter(){
        return new Adapter(eventSolver(), smartHome(), sensorEventGetters());
    }

    @Bean
    SensorEventGetter doorSensorEventGetter(){
        return new DoorSensorEventGetterImpl(getTypeReturner());
    }

    @Bean
    SensorEventGetter lightSensorEventGetter(){
        return new LightSensorEventGetterImpl(getTypeReturner());
    }

    @Bean()
    SmartHome smartHome() {
        return new HomeConditionImplementation().smartHomeCondition();
    }

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventSolverImplementationAdapter());
        return sensorEventsManager;
    }

    @Bean
    EventSolver alarmEventHandler(SmartHome smartHome) {
        return new AlarmEventHandler(smartHome);
    }

    @Bean
    GeneralEvent lightEventHandler() {
        return new LightEventHandler();
    }

    @Bean
    GeneralEvent doorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    GeneralEvent hallDoorEventHandler() {
        return new ClosedHallDoorEventHandler();
    }

    @Bean
    EventGenerator eventGenerator(){
        return new EventGenerator();
    }

    @Bean
    EventProcessor eventProcessor(){
        return new EventProcessor(smartHome(), eventGenerator(), eventSolver(), eventSolverDecorator());
    }

    @Bean
    List<GeneralEvent> eventHandlersList(){
        return Arrays.asList(doorEventHandler(), lightEventHandler(), hallDoorEventHandler());
    }

    @Bean
    Alarm alarm(){
        return new Alarm(1111);
    }

    @Bean
    GeneralEvent eventSolverDecorator(){
        return new EventSolverDecorator(eventHandlersList());
    }

    @Bean
    Map<String, SensorEventType> getTypeReturner() {
        return new HashMap<String, SensorEventType>() {
            {
                put("LightIsOn", SensorEventType.LIGHT_ON);
                put("LightIsOff", SensorEventType.LIGHT_OFF);
                put("DoorIsOpen", SensorEventType.DOOR_OPEN);
                put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
                put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
                put("DoorIsUnLocked", SensorEventType.DOOR_UNLOCKED);
            }
        };
    }

    @Bean
    Collection<SensorEventGetter> sensorEventGetters(){
        return Arrays.asList(doorSensorEventGetter(), lightSensorEventGetter());
    }
}
