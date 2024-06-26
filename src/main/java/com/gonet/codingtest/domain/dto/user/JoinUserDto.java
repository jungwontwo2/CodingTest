package com.gonet.codingtest.domain.dto.user;

import com.gonet.codingtest.domain.entity.Department;
import com.gonet.codingtest.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoinUserDto {
    @NotBlank(message = "아이디를 입력하세요")
    @Size(min = 4,max = 10,message = "최소 4자 이상, 10자 이하로 입력하세요")
    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 소문자(a~z), 숫자(0~9)만 입력 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min = 8,max = 20,message = "최소 8자 이상, 20자 이하로 입력하세요")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "알파벳 대소문자(a~z, A~Z), 숫자(0~9)만 입력 가능합니다.")
    private String password;
    @NotBlank(message = "이름을 입력하세요")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 한글, 영어만 가능합니다.")
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
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
        // 기타 필요한 필드 복사 작업 수행
        return user;
    }
}
