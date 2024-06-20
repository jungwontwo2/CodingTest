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
    private final DepartmentRepository departmentRepository;
    @PostConstruct
    public void dataInit(){
        Department findBackend = departmentRepository.findbyName("백엔드").orElse(null);
        Department findPublisher = departmentRepository.findbyName("퍼블리셔").orElse(null);
        Department findPlanner = departmentRepository.findbyName("기획자").orElse(null);
        Department findDesigner = departmentRepository.findbyName("디자이너").orElse(null);

        if(findBackend==null){
            Department backend = new Department("백엔드");
            departmentRepository.save(backend);
        }
        if(findPublisher==null){
            Department publisher = new Department("퍼블리셔");
            departmentRepository.save(publisher);
        }
        if(findPlanner==null){
            Department planner = new Department("기획자");
            departmentRepository.save(planner);
        }
        if(findDesigner==null){
            Department designer = new Department("디자이너");
            departmentRepository.save(designer);
        }
        System.out.println("department init complete");
    }

}
