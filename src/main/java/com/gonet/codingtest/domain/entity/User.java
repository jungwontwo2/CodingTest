package com.gonet.codingtest.domain.entity;

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

}
