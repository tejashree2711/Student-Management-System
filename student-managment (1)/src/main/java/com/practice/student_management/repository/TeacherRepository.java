package com.practice.student_management.repository;

import com.practice.student_management.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
//    Page<Teacher> findAll(Pageable pageable);


     @Query(value="SELECT * FROM teacher where reg_no=:integer",nativeQuery = true)
    Teacher findByRegNo(Integer integer);
}
