package com.practice.student_management.teacher;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Student;
import com.practice.student_management.model.Teacher;
import com.practice.student_management.model.UserDto;
import com.practice.student_management.repository.StudentRepository;
import com.practice.student_management.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommonResponse<Teacher> saveTeacher(Teacher teacher) {
        CommonResponse<Teacher> response = new CommonResponse<>();

        if (teacher == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("teacher must not be null.");
        }

        if (teacher.getT_name() == null || teacher.getT_email() == null || teacher.getT_address() == null
                || teacher.getGender() == null || teacher.getJoin_date() == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("field must not be null.");
        }

        if (teacher.getT_id() == null) {
            Teacher save = teacherRepository.save(teacher);
            response.setSuccess(true);
            response.setResponseCode(HttpStatus.CREATED.value());
            response.setData(save);
            response.setResponseMessage("teacher created successfully.");
        } else {
            Optional<Teacher> optional = teacherRepository.findById(teacher.getT_id());
            if (optional.isPresent()) {
                Teacher map = modelMapper.map(teacher, Teacher.class);
                Teacher save = teacherRepository.save(map);

                response.setSuccess(true);
                response.setResponseCode(HttpStatus.OK.value());
                response.setData(save);
                response.setResponseMessage("teacher updated successfully.");
            }
        }

        return response;
    }

    @Override
    public CommonResponse<List<Teacher>> getAllTeachers() {
        CommonResponse<List<Teacher>> response = new CommonResponse<>();

        List<Teacher> list = teacherRepository.findAll();
        response.setSuccess(true);
        response.setResponseCode(HttpStatus.OK.value());
        response.setData(list);
        response.setResponseMessage("teachers retrieved successfully.");
        return response;
    }

    @Override
    public CommonResponse<Teacher> getTeacherById(Integer id) {
        CommonResponse<Teacher> response = new CommonResponse<>();

        Optional<Teacher> optional = teacherRepository.findById(id);
        if (optional.isPresent()) {
            Teacher teacher = optional.get();

            response.setSuccess(true);
            response.setResponseCode(HttpStatus.FOUND.value());
            response.setData(teacher);
            response.setResponseMessage("teacher found successfully.");
        } else {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("teacher not found");
        }
        return response;
    }

    @Override
    public CommonResponse<Teacher> deleteTeacherById(Integer id) {
        CommonResponse<Teacher> response = new CommonResponse<>();

        if (id == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("id must not be null.");
            return response;
        }
        Optional<Teacher> optional = teacherRepository.findById(id);
        if (optional.isPresent()) {
            Teacher teacher = optional.get();
            if (teacher.getStatus() == 0) {
                response.setSuccess(false);
                response.setResponseCode(HttpStatus.BAD_REQUEST.value());
                response.setResponseMessage("teacher is already deleted.");
            } else {
                teacher.setStatus(0);
                teacherRepository.save(teacher);

                response.setData(teacher);
                response.setResponseCode(HttpStatus.OK.value());
                response.setSuccess(true);
                response.setResponseMessage("teacher deleted successfully.");
            }
        } else {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("teacher not found.");
        }
        return response;
    }

    @Override
    public CommonResponse<UserDto> login(Integer regNo, String mobileNo,String type) {
        CommonResponse<UserDto> response=new CommonResponse<>();

        if(type==null){
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
            return response;
        }
        if (regNo == null || mobileNo == null) {

            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("email or password must not be null.");
            return response;
        }
          if(type.equalsIgnoreCase("teacher")){

              Teacher teacher = teacherRepository.findByRegNo(regNo);

              if (teacher != null && teacher.getReg_no() != null) {

                  String pass= teacher.getMobile_no();

                  if(pass==null){
                      response.setResponseCode(HttpStatus.NOT_FOUND.value());
                      response.setSuccess(false);
                      response.setResponseMessage("Incorrect password");
                      return response;
                  }
                  boolean val = pass.equals(mobileNo);
                  if (val) {
                      response.setResponseCode(HttpStatus.FOUND.value());
                      response.setSuccess(true);
                      response.setResponseMessage("login successfully");

                      UserDto userDto=new UserDto();
                      userDto.setUId(teacher.getT_id());
                      userDto.setURole(teacher.getRole());
                      userDto.setUName(teacher.getT_name());

                      response.setData(userDto);
                      return response;
                  } else {
                      response.setResponseCode(HttpStatus.NOT_FOUND.value());
                      response.setSuccess(false);
                      response.setResponseMessage("Incorrect password");
                      return response;
                  }

              } else {
                  response.setResponseCode(HttpStatus.NOT_FOUND.value());
                  response.setSuccess(false);
                  response.setResponseMessage("Incorrect register no.");
                  return response;
              }
          }else
          if(type.equalsIgnoreCase("student")){

              Student student=studentRepository.findByRegNo(regNo);

              if (student != null && student.getReg_no() != null) {

                  String pass= student.getMobile_no();

                  if(pass==null){
                      response.setResponseCode(HttpStatus.NOT_FOUND.value());
                      response.setSuccess(false);
                      response.setResponseMessage("Incorrect password");
                      return response;
                  }
                  boolean val = pass.equals(mobileNo);
                  if (val) {
                      response.setResponseCode(HttpStatus.FOUND.value());
                      response.setSuccess(true);
                      response.setResponseMessage("login successfully");

                      UserDto userDto=new UserDto();
                      userDto.setUId(student.getS_id());
                      userDto.setURole("student");
                      userDto.setUName(student.getFirstName());

                      response.setData(userDto);
                      return response;
                  } else {
                      response.setResponseCode(HttpStatus.NOT_FOUND.value());
                      response.setSuccess(false);
                      response.setResponseMessage("Incorrect password");
                      return response;
                  }

              } else {
                  response.setResponseCode(HttpStatus.NOT_FOUND.value());
                  response.setSuccess(false);
                  response.setResponseMessage("Incorrect register no.");
                  return response;
              }
          }
        return response;
    }
}
