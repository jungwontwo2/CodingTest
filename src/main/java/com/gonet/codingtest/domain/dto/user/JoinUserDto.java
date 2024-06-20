package com.gonet.codingtest.domain.dto.user;

import com.gonet.codingtest.domain.entity.Department;
import com.gonet.codingtest.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoinUserDto {
    @NotBlank(message = "아이디를 입력하세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @NotBlank(message = "이름을 입력하세요")
    private String name;
    @NotNull(message = "부서를 선택하세요")
    private Long departmentId;

    private String email;

    private String phone;
    public static User toEntity(String username, String password, String name, Department department,String email,String phone) {
        User user = User.builder()
                .username(username)
                .password(password)
                .name(name)
                .department(department)
                .email(email)
                .phone(phone)
                .isDeleted(false)
                .createdTime(LocalDateTime.now())
                .editedTime(null)
                .build();
        // 기타 필요한 필드 복사 작업 수행
        return user;
    }
}
