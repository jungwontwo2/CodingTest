package com.gonet.codingtest.service;

import com.gonet.codingtest.domain.dto.user.CustomUserDetails;
import com.gonet.codingtest.domain.entity.User;
import com.gonet.codingtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user!=null){
            return new CustomUserDetails(user);
        }
        System.out.println("return null");
        return null;
    }
}
