package com.gonet.codingtest.domain.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditUserDto {
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
}
