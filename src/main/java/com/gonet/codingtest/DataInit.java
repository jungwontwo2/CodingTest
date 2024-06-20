package com.gonet.codingtest;

import com.gonet.codingtest.domain.entity.Department;
import com.gonet.codingtest.repository.DepartmentRepository;
import com.gonet.codingtest.service.DepartmentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInit {
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @PostConstruct
    public void dataInit(){
        Department backend = new Department("백엔드");
        Department publisher = new Department("퍼블리셔");
        Department planner = new Department("기획자");
        Department designer = new Department("디자이너");
        departmentRepository.save(backend);
        departmentRepository.save(publisher);
        departmentRepository.save(planner);
        departmentRepository.save(designer);
        System.out.println("department init complete");
    }

}
