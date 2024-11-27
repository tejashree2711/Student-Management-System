package com.practice.student_management.classes;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Class;
import com.practice.student_management.repository.ClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassRepository classRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommonResponse<Class> saveClass(Class classes) {
        CommonResponse<Class> response = new CommonResponse<>();

        if (classes == null || classes.getC_name() == null || classes.getC_desc() == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("class must not be empty.");
            return response;
        }
        if (classes.getC_id() == null) {
            Class save = classRepository.save(classes);

            response.setSuccess(true);
            response.setData(save);
            response.setResponseCode(HttpStatus.OK.value());
            response.setResponseMessage("class saved successfully.");
            return response;
        } else {
            Optional<Class> optional = classRepository.findById(classes.getC_id());
            if (optional.isPresent()) {
                Class map = modelMapper.map(classes, Class.class);
                Class save = classRepository.save(map);

                response.setSuccess(true);
                response.setData(save);
                response.setResponseCode(HttpStatus.OK.value());
                response.setResponseMessage("class saved successfully.");
            } else {
                response.setSuccess(false);
                response.setResponseCode(HttpStatus.NOT_FOUND.value());
                response.setResponseMessage("class not found.");
            }
            return response;
        }
    }

    @Override
    public CommonResponse<Class> getClassById(Integer id) {
        CommonResponse<Class> response = new CommonResponse<>();

        Optional<Class> optional = classRepository.findById(id);
        if (!optional.isPresent()) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("class not found.");
        }else {
            response.setSuccess(true);
            response.setData(optional);
            response.setResponseCode(HttpStatus.FOUND.value());
            response.setResponseMessage("class found successfully.");
        }
        return response;
    }

    @Override
    public CommonResponse<List<Class>> getAllClasses() {
        CommonResponse<List<Class>> response = new CommonResponse<>();

        List<Class> all = classRepository.findAll();
        if (all.isEmpty()) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("classes not found.");
        }
        response.setSuccess(true);
        response.setData(all);
        response.setResponseCode(HttpStatus.FOUND.value());
        response.setResponseMessage("classes found successfully.");
        return response;
    }
}
