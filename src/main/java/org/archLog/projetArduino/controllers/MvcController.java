package org.archLog.projetArduino.controllers;

import org.archLog.projetArduino.models.User;
import org.archLog.projetArduino.repositories.ArduinoRepository;
import org.archLog.projetArduino.repositories.LogRepository;
import org.archLog.projetArduino.services.ArduinoService;
import org.archLog.projetArduino.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MvcController {

    @Autowired
    UserService userService;
    @Autowired
    ArduinoRepository arduinoRepository;
    @Autowired
    LogRepository logRepository;

    @Autowired
    ArduinoService arduinoService;

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "There is already an admin registered with the email provided");
        }
        userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "There is already an admin registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.createUser(user);
            modelAndView.addObject("successMessage", "Admin has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @GetMapping("/admin/arduino/mode")
    public ModelAndView mode(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("arduino", arduinoRepository.getOne(1));
        modelAndView.setViewName("mode");
        return modelAndView;
    }

    @GetMapping("/arduino/state")
    public ModelAndView state(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("arduino", arduinoRepository.getOne(1));
        modelAndView.setViewName("state");
        return modelAndView;
    }

    @GetMapping("/arduino/log")
    public ModelAndView log(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("logs", logRepository.findAll());
        modelAndView.setViewName("log");
        return modelAndView;
    }
    @GetMapping("/arduino/charts")
    public  ModelAndView charts(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testchart");
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
        modelAndView.addObject("data", result);

        return modelAndView;
    }

}