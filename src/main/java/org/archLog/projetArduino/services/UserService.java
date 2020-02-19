package org.archLog.projetArduino.services;

import org.archLog.projetArduino.models.Authority;
import org.archLog.projetArduino.models.User;
import org.archLog.projetArduino.repositories.AuthorityRepository;
import org.archLog.projetArduino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthorityRepository authorityRepository;

    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Authority> list = new ArrayList<Authority>();
        list.add(authorityRepository.findByAuthority("ADMIN"));
        user.setAuthorities(list);
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
