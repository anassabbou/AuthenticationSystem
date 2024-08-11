package com.abbou.services;


import com.abbou.models.ApplicationUser;
import com.abbou.models.Role;
import com.abbou.repository.RoleRepository;
import com.abbou.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ApplicationUser registerUser(String username, String password){
        String encodedPassword= passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities= new HashSet<>();

        authorities.add(userRole);
        return userRepository.save(new ApplicationUser(0,username,encodedPassword, authorities));
    }




}
