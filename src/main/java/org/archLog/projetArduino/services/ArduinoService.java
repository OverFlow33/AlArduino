package org.archLog.projetArduino.services;

import org.archLog.projetArduino.elements.Adapter;
import org.archLog.projetArduino.models.Arduino;
import org.archLog.projetArduino.models.Log;
import org.archLog.projetArduino.repositories.ArduinoRepository;
import org.archLog.projetArduino.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.*;

@Service
public class ArduinoService {
    @Autowired
    LogRepository logRepository;
    @Autowired
    ArduinoRepository arduinoRepository;
    @Autowired
    Adapter adapter;

    public String changeState() throws InterruptedException {
        Arduino arduino = arduinoRepository.getOne(1);
        arduino.setManual(true);
        if(arduino.getState().equals("ON")){
            arduino.setState("OFF");
            logRepository.save(new Log(true, "OFF"));
            adapter.sendByte(122);
        } else {
            arduino.setState("ON");
            logRepository.save(new Log(true, "ON"));
            adapter.sendByte(97);
        }
        arduinoRepository.save(arduino);
        String state = arduino.getState();
        return "{message:'LED TURNED "+state+"', state: '"+state+"'}";
    }

    public Map<String, String> switchMode() throws InterruptedException {
        Arduino arduino = arduinoRepository.getOne(1);
        String m = "";
        if(arduino.isManual()){
            arduino.setManual(false);
            arduino.setState("AUTO");
            logRepository.save(new Log(false, "AUTO"));
            adapter.sendByte(55);
            m = "automatic mode enabled";
        } else {
            arduino.setManual(true);
            arduino.setState("OFF");
            logRepository.save(new Log(true, "OFF"));
            adapter.sendByte(33);
            m = "manual mode enabled";
        }
        arduinoRepository.save(arduino);
        Map<String, String> map = new HashMap<>();
        map.put("mode", arduino.isManual() + "");
        map.put("message", m);
        return map;
        //return "{message:'"+m+"', mode: '"+arduino.isManual()+"'}";
    }
    public ArrayList<Integer> countByCreatedBetween(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        Date debut ;
        Date fin ;
        for (int i = 0; i < 24; i++) {
            debut = new Date(LocalDate.now().toString());
            debut.setMinutes(00);
            debut.setSeconds(00);
            debut.setHours(i);
            fin = new Date(LocalDate.now().toString());
            fin.setMinutes(00);
            fin.setSeconds(00);
            fin.setHours(i+1);
            result.add(logRepository.findByCreatedBetween(debut,fin).size());
        }
        return result;
    }

}
