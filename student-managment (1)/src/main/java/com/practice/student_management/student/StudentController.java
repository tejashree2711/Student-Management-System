package com.practice.student_management.student;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("saveStudent")
    public CommonResponse<Student> saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("getStudentById")
    public CommonResponse<Student> getStudentByid(@RequestParam Integer s_id) {
        return studentService.getStudentById(s_id);
    }

    @GetMapping("getAllStudents")
    public CommonResponse<List<Student>> getAll(@RequestParam Integer classId) {
        return studentService.getAll(classId);
    }

    @DeleteMapping("deleteStudentById")
    public CommonResponse<Student> deleteById(@RequestParam Integer id) {
        return studentService.deleteById(id);
    }

}
