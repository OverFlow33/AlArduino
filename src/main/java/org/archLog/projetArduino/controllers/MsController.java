package org.archLog.projetArduino.controllers;

import org.archLog.projetArduino.repositories.LogRepository;
import org.archLog.projetArduino.services.ArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@RestController
public class MsController {

    @Autowired
    ArduinoService arduinoService;
    @Autowired
    LogRepository logRepository;

    @GetMapping("/arduino/change")
    public String change() throws InterruptedException {
        return arduinoService.changeState();
    }

    @GetMapping("/arduino/switch")
    public Map<String, String> switchMode() throws InterruptedException {
        return arduinoService.switchMode();
    }

    @GetMapping("/logs")
    public ArrayList<Integer> countAllByCreatedBetween(){
        ArrayList<Integer> result = new ArrayList<>();
        Date debut;
        Date fin ;
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 24; i++) {
            debut = calendar.getTime();
            debut.setMinutes(00);
            debut.setSeconds(00);
            debut.setHours(i);
            fin = calendar.getTime();
            fin.setMinutes(00);
            fin.setSeconds(00);
            fin.setHours(i+1);
            result.add(logRepository.findByCreatedBetween(debut,fin).size());
        }
        return result;
    }

}
