package com.practice.student_management.classes;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassController {

    @Autowired
    ClassService classService;

    @PostMapping("saveClass")
    public CommonResponse<Class> saveClass(@RequestBody Class classes) {
        return classService.saveClass(classes);
    }

    @GetMapping("getClassById")
    public CommonResponse<Class> getClassById(@RequestParam Integer id) {
        return classService.getClassById(id);
    }

    @GetMapping("getAllClasses")
    public CommonResponse<List<Class>> getAllClasses() {
        return classService.getAllClasses();
    }
}
