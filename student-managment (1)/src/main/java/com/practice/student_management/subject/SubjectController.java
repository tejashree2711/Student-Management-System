package com.practice.student_management.subject;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping("saveSubject")
    public CommonResponse<Subject> saveSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @GetMapping("getAllSubjects")
    public CommonResponse<List<Subject>> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("getSubById")
    public CommonResponse<Subject> getById(@RequestParam Integer id) {
        return subjectService.getById(id);
    }
}
