package com.practice.student_management.subject;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Subject;

import java.util.List;

public interface SubjectService {
    CommonResponse<Subject> saveSubject(Subject subject);
    CommonResponse<List<Subject>> getAllSubjects();
    CommonResponse<Subject> getById(Integer id);
}