package com.gonet.codingtest.service;

import com.gonet.codingtest.domain.entity.User;
import com.gonet.codingtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User login(String username, String password){
        System.out.println("zzzz");
        Optional<User> user = userRepository.findByUsername(username);
        if(bCryptPasswordEncoder.matches(password,user.get().getPassword())){
            return user.get();
        }
        else {
            return null;
        }
    }
}
