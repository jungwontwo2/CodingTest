package com.gonet.codingtest.domain.entity;

import com.gonet.codingtest.domain.dto.user.EditUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    @ManyToOne
    private Department department;
    private boolean isDeleted;
    private LocalDateTime createdTime;
    private LocalDateTime editedTime;
    public void updateUser(String username,String password,String name,String email,String phone,Department department){
        this.username=username;
        this.password=password;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.department=department;
        this.editedTime=LocalDateTime.now();
    }

    public void delete() {
        this.isDeleted=true;
    }
}
