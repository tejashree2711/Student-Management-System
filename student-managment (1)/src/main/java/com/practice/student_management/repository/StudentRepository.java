package com.practice.student_management.repository;

import com.practice.student_management.model.Class;
import com.practice.student_management.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(
            value = "SELECT * FROM student s WHERE s.c_id = :classId and status=1",
            nativeQuery = true
    )
    List<Student> findByC_IdAndStatus(Integer classId);
    @Query(
            value = "SELECT * FROM student s WHERE s.reg_no= :regNo",
            nativeQuery = true
    )
    Student findByRegNo(Integer regNo);
}
