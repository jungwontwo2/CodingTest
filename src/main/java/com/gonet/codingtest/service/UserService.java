package com.gonet.codingtest.service;

import com.gonet.codingtest.domain.dto.user.JoinUserDto;
import com.gonet.codingtest.domain.entity.Department;
import com.gonet.codingtest.domain.entity.User;
import com.gonet.codingtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DepartmentService departmentService;

    public void saveUser(JoinUserDto user) {
        Department department = departmentService.findDepartmentById(user.getDepartmentId());
        String email = (user.getEmail() != null && !user.getEmail().isEmpty()) ? user.getEmail() : null;
        String phone = (user.getPhone() != null && !user.getPhone().isEmpty()) ? user.getPhone() : null;

        User userEntity = JoinUserDto.toEntity(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                user.getName(), department,email,phone);
        userRepository.save(userEntity);

    }

    public User getUserByLoginId(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

//    public void saveUser(JoinUserDto user) {
//        JoinUserDto.toEntity(user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone(),user.getde)
//    }
}
