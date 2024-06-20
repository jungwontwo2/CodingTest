package com.gonet.codingtest.service;

import com.gonet.codingtest.domain.dto.user.EditUserDto;
import com.gonet.codingtest.domain.dto.user.JoinUserDto;
import com.gonet.codingtest.domain.entity.Department;
import com.gonet.codingtest.domain.entity.User;
import com.gonet.codingtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void updateUser(String username,EditUserDto editUserDto) {
        User user = userRepository.findByUsername(username).orElse(null);
        Department department = departmentService.findDepartmentById(editUserDto.getDepartmentId());
        String email = (editUserDto.getEmail() != null && !editUserDto.getEmail().isEmpty()) ? editUserDto.getEmail() : null;
        String phone = (editUserDto.getPhone() != null && !editUserDto.getPhone().isEmpty()) ? editUserDto.getPhone() : null;
        user.updateUser(editUserDto.getUsername(), bCryptPasswordEncoder.encode(editUserDto.getPassword()), editUserDto.getName(),
                email, phone, department);
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        user.delete();
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
