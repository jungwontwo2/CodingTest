package com.gonet.codingtest.controller;

import com.gonet.codingtest.domain.dto.ResponseDto;
import com.gonet.codingtest.domain.dto.user.CustomUserDetails;
import com.gonet.codingtest.domain.dto.user.EditUserDto;
import com.gonet.codingtest.domain.dto.user.JoinUserDto;
import com.gonet.codingtest.domain.dto.user.LoginUserDto;
import com.gonet.codingtest.domain.entity.User;
import com.gonet.codingtest.service.DepartmentService;
import com.gonet.codingtest.service.LoginService;
import com.gonet.codingtest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final DepartmentService departmentService;
    private final LoginService loginService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("users/join")
    public String addUserForm(@ModelAttribute("user") JoinUserDto user, Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "users/addUserForm";
    }

    @RequestMapping(value = "/join/loginIdCheck")
    public @ResponseBody ResponseDto<?> check(@RequestBody(required = false) String username) {
        if (username == null || username.isEmpty()) {
            return new ResponseDto<>(-1, "아이디를 입력해주세요.", null);
        }
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return new ResponseDto<>(1, "사용 가능한 ID입니다.", true);
        } else {
            return new ResponseDto<>(1, "중복된 아이디입니다.", false);
        }
    }

    @PostMapping("users/join")
    public String addUser(@Validated @ModelAttribute("user") JoinUserDto user, BindingResult bindingResult, Model model) {
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!Pattern.matches(emailPattern, user.getEmail())) {
                bindingResult.rejectValue("email", "error.email", "유효한 이메일 주소를 입력하세요 ex:xxx@naver.com");
            }
        }

        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            String phonePattern = "^[0-9]{10,11}$";
            if (!Pattern.matches(phonePattern, user.getPhone())) {
                bindingResult.rejectValue("phone", "error.phone", "유효한 전화번호를 입력하세요 ex:01012341234");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "users/addUserForm";
        }

        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("users/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model, @ModelAttribute("user") LoginUserDto user) {
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("exception", exception);
        }
        return "users/login";
    }

    @PostMapping("users/login")
    public String PostLogin(@Validated @ModelAttribute("user") LoginUserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "users/login";
        }
        User useEntity = loginService.login(user.getUsername(), user.getPassword());
        if (useEntity == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "users/login";
        }
        return "redirect:/";
    }

    @GetMapping("users/edit/info")
    public String editForm(@ModelAttribute("editUserDto") EditUserDto editUserDto, Model model, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        model.addAttribute("user",userService.getUserByUsername(username));
        model.addAttribute("departments",departmentService.getAllDepartments());
        return "users/user-info-edit";
    }
    @PostMapping("users/edit/info")
    public String editUserInfo(@Validated @ModelAttribute("editUserDto") EditUserDto editUserDto,BindingResult bindingResult,
                               Authentication authentication,Model model){
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        if (editUserDto.getEmail() != null && !editUserDto.getEmail().isEmpty()) {
            String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!Pattern.matches(emailPattern, editUserDto.getEmail())) {
                bindingResult.rejectValue("email", "error.email", "유효한 이메일 주소를 입력하세요 ex:xxx@naver.com");
            }
        }

        if (editUserDto.getPhone() != null && !editUserDto.getPhone().isEmpty()) {
            String phonePattern = "^[0-9]{10,11}$";
            if (!Pattern.matches(phonePattern, editUserDto.getPhone())) {
                bindingResult.rejectValue("phone", "error.phone", "유효한 전화번호를 입력하세요 ex:01012341234");
            }
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("user",userService.getUserByUsername(username));
            return "users/user-info-edit";
        }
        userService.updateUser(username,editUserDto);
        return "redirect:/";
    }
    @PostMapping("users/delete")
    public String deleteUser(HttpServletRequest request, HttpServletResponse response,Authentication authentication){
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        userService.deleteUser(username);
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        return "redirect:/";
    }
}
