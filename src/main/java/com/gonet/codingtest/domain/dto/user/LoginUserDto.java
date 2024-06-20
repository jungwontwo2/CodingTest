package com.gonet.codingtest.domain.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotBlank(message = "아이디를 입력하세요")
    private String username;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
}
