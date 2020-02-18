package org.archLog.projetArduino.services;

import org.archLog.projetArduino.models.Authority;
import org.archLog.projetArduino.models.User;
import org.archLog.projetArduino.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1 = userRepository.findByEmail(username);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        for (Authority authority: user1.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
            grantList.add(grantedAuthority);
        }
        UserDetails user = new org.springframework.security.core.userdetails.User(user1.getEmail(), user1.getPassword(), grantList);
        return user;
    }
}