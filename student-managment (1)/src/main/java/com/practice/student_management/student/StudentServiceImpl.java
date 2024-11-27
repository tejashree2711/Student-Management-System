package com.practice.student_management.student;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Class;
import com.practice.student_management.model.Student;
import com.practice.student_management.repository.ClassRepository;
import com.practice.student_management.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommonResponse<Student> saveStudent(Student student) {
        CommonResponse<Student> response = new CommonResponse();

        if (student == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("student must not be empty.");
            return response;
        }
        if (student.getAge() == null || student.getFirstName() == null || student.getMiddleName() == null || student.getLastName() == null
                || student.getMobile_no() == null || student.getReg_no() == null || student.getGender() == null || student.getPermanent_add() == null || student.getRoll_no() == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("field must not be empty.");
            return response;
        }

        if (student.getS_id() == null) {
            Student save = studentRepository.save(student);

            response.setSuccess(true);
            response.setResponseCode(HttpStatus.CREATED.value());
            response.setResponseMessage("Student created successfully.");
            response.setData(save);
        } else {
            Optional<Student> optional = studentRepository.findById(student.getS_id());
            if (optional.isPresent()) {
                Student map = modelMapper.map(student, Student.class);
                Student student1 = studentRepository.save(map);

                response.setSuccess(true);
                response.setResponseCode(HttpStatus.OK.value());
                response.setResponseMessage("Student updated successfully.");
                response.setData(student1);
            } else {
                response.setSuccess(false);
                response.setResponseCode(HttpStatus.NOT_FOUND.value());
                response.setResponseMessage("Student not found.");
            }
        }
        return response;
    }

    @Override
    public CommonResponse<Student> getStudentById(Integer s_id) {
        CommonResponse<Student> response = new CommonResponse<>();
        Optional<Student> student = studentRepository.findById(s_id);

        if (!student.isPresent()) {
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            response.setResponseMessage("student is not present");
            return response;
        }
        Student student1 = student.get();
        Student response1 = modelMapper.map(student1, Student.class);
        response.setResponseCode(HttpStatus.FOUND.value());
        response.setData(student1);
        response.setSuccess(true);
        response.setResponseMessage("student is present");

        return response;
    }

    @Override
    public CommonResponse<List<Student>> getAll(Integer classId) {
        CommonResponse<List<Student>> response = new CommonResponse<>();

//        Pageable pageable =PageRequest.of(pageNO,pageSize);
        List<Student> students = studentRepository.findByC_IdAndStatus(classId);

        if (students.isEmpty()) {
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            response.setResponseMessage("students not found,");
            return response;
        }

        response.setResponseCode(HttpStatus.OK.value());
        response.setSuccess(true);
        response.setResponseMessage("students retrieved successfully.");
        response.setData(students);
        return response;
    }

    @Override
    public CommonResponse<Student> deleteById(Integer id) {
        CommonResponse<Student> response = new CommonResponse<>();

        if (id == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("id must not be null.");
            return response;
        }
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student student = optional.get();
            if (student.getStatus() == 0) {
                response.setSuccess(false);
                response.setResponseCode(HttpStatus.BAD_REQUEST.value());
                response.setResponseMessage("student is already deleted.");
            } else {
                student.setStatus(0);
                studentRepository.save(student);

                response.setData(student);
                response.setResponseCode(HttpStatus.OK.value());
                response.setSuccess(true);
                response.setResponseMessage("student deleted successfully.");
            }
        } else {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("student not found.");
        }
        return response;
    }
}
