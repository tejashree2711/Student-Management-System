package com.practice.student_management.teacher;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Teacher;
import com.practice.student_management.model.UserDto;

import java.util.List;

public interface TeacherService {
    CommonResponse<Teacher> saveTeacher(Teacher teacher);
    CommonResponse<List<Teacher>> getAllTeachers();
    CommonResponse<Teacher> getTeacherById(Integer id);
    CommonResponse<Teacher> deleteTeacherById(Integer id);
    CommonResponse<UserDto> login(Integer regNo,String mobile_no,String type);

}
