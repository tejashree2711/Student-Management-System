package com.practice.student_management.classes;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Class;

import java.util.List;

public interface ClassService {
    CommonResponse<Class> saveClass(Class classes);
    CommonResponse<Class> getClassById(Integer id);
    CommonResponse<List<Class>> getAllClasses();
}
