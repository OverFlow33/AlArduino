package org.archLog.projetArduino.config;

import org.archLog.projetArduino.elements.Adapter;
import org.archLog.projetArduino.models.Arduino;
import org.archLog.projetArduino.repositories.ArduinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    ArduinoRepository arduinoRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder(31);
    }
    @Bean
    public Adapter adapter(){
        return new Adapter(arduinoRepository.getOne(1));
    }

}