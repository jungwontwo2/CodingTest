package com.gonet.codingtest.repository;

import com.gonet.codingtest.domain.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select d from Department d where d.departmentName = :name")
    Optional<Department> findbyName(@Param("name") String name);
}
