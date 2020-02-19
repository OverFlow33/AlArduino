package org.archLog.projetArduino.controllers;

import org.archLog.projetArduino.services.ArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class MsController {

    @Autowired
    ArduinoService arduinoService;

    @GetMapping("/arduino/change")
    public String change() throws InterruptedException {
        return arduinoService.changeState();
    }

    @GetMapping("/arduino/switch")
    public Map<String, String> switchMode() throws InterruptedException {
        return arduinoService.switchMode();
    }

}
