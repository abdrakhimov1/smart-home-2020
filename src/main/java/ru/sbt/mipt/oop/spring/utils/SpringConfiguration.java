package ru.sbt.mipt.oop.spring.utils;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.RemoteControlI;
import ru.sbt.mipt.oop.api.adapter.Adapter;
import ru.sbt.mipt.oop.api.adapter.DoorSensorEventGetterImpl;
import ru.sbt.mipt.oop.api.adapter.LightSensorEventGetterImpl;
import ru.sbt.mipt.oop.condition.HomeConditionImplementation;
import ru.sbt.mipt.oop.event_handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.rc.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringConfiguration {

    @Bean
    EventSolverWithEvents eventSolver(){
        return new EventSolverImplementation(lightEventHandler(), doorEventHandler());
    }

    @Bean
    Adapter eventSolverImplementationAdapter(){
        return new Adapter(eventSolver(), smartHome(), doorSensorEventGetter(), lightSensorEventGetter());
    }

    @Bean
    DoorSensorEventGetterImpl doorSensorEventGetter(){
        return new DoorSensorEventGetterImpl(getTypeReturner());
    }

    @Bean
    LightSensorEventGetterImpl lightSensorEventGetter(){
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
        return new EventProcessor(smartHome(), eventSolver(), eventGenerator());
    }

    @Bean
    int alarmCode() {
        return 1111;
    }

    @Bean
    Command activateAlarm(SmartHome smartHome, int alarmCode) {
        return new ActivateAlarmCommand(alarmCode, smartHome);
    }

    @Bean
    Command closeHallDoor(SmartHome smartHome) {
        return new CloseHallDoor(smartHome);
    }

    @Bean
    Command turnOffAllLights(SmartHome smartHome) {
        return new TurnOffAllLightsCommand(smartHome);
    }

    @Bean
    Command turnOnAlarmingMode() {
        return new ActivateWarningState();
    }

    @Bean
    Command turnOnAllLights(SmartHome smartHome) {
        return new TurnOnAllLightsCommand(smartHome);
    }

    @Bean
    Command turnOffHallLight(SmartHome smartHome) {
        return new TurnOffHallLight(smartHome);
    }

    @Bean
    public RemoteControlI remoteControl(Command activateAlarm,
                                        Command turnOnAlarmingMode,
                                        Command closeHallDoor,
                                        Command turnOffHallLight,
                                        Command turnOnAllLights,
                                        Command turnOffAllLights) {
        RemoteControlI remoteControl = new RemoteControlI();
        remoteControl.setCommand("A", activateAlarm);
        remoteControl.setCommand("B", turnOnAlarmingMode);
        remoteControl.setCommand("C", closeHallDoor);
        remoteControl.setCommand("D", turnOffHallLight);
        remoteControl.setCommand("1", turnOnAllLights);
        remoteControl.setCommand("2", turnOffAllLights);
        return remoteControl;
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
    String remoteControllerId() {
        return "1234";
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry(RemoteControl remoteControl, String remoteControllerId) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl, remoteControllerId);
        return remoteControlRegistry;
    }
}
