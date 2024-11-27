package com.practice.student_management.subject;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Subject;
import com.practice.student_management.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public CommonResponse<Subject> saveSubject(Subject subject) {
        CommonResponse<Subject> response = new CommonResponse<>();

        if (subject == null || subject.getS_name() == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("subject must not be empty.");
            return response;
        }
        subjectRepository.save(subject);
        response.setSuccess(true);
        response.setData(subject);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseMessage("subject saved successfully.");
        return response;
    }

    @Override
    public CommonResponse<List<Subject>> getAllSubjects() {
        CommonResponse<List<Subject>> response = new CommonResponse<>();
        List<Subject> all = subjectRepository.findAll();

        response.setSuccess(true);
        response.setResponseCode(HttpStatus.OK.value());
        response.setData(all);
        response.setResponseMessage("subjects retrieved successfully.");
        return response;
    }

    @Override
    public CommonResponse<Subject> getById(Integer id) {
        CommonResponse<Subject> response = new CommonResponse<>();

        Optional<Subject> optional = subjectRepository.findById(id);
        if (optional.isPresent()) {
            Subject subject = optional.get();

            response.setSuccess(true);
            response.setResponseCode(HttpStatus.OK.value());
            response.setData(subject);
            response.setResponseMessage("subject retrieved successfully.");
        }
        else {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("subject not found");
        }
        return response;
    }
}
