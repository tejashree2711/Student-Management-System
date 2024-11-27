package com.practice.student_management.teacher;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping("saveTeacher")
    public CommonResponse<Teacher> saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @GetMapping("getAllTeachers")
    public CommonResponse<List<Teacher>> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("getTeacherById")
    public CommonResponse<Teacher> getTeacherById(@RequestParam Integer id) {
        return teacherService.getTeacherById(id);
    }

    @DeleteMapping("deleteTeacherById")
    public CommonResponse<Teacher> deleteTeacherById(@RequestParam Integer id) {
        return teacherService.deleteTeacherById(id);
    }
}
