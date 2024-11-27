package com.practice.student_management.student;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    public CommonResponse<Student> saveStudent(Student student);
    public  CommonResponse<Student> getStudentById(Integer s_id);
    public  CommonResponse<List<Student>> getAll(Integer classId);
    public CommonResponse<Student> deleteById(Integer id);
}
