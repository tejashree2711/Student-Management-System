package com.practice.student_management.Leave;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.common.StatusConstant;
import com.practice.student_management.model.Leaves;
import com.practice.student_management.model.Teacher;
import com.practice.student_management.repository.LeaveRepository;
import com.practice.student_management.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public CommonResponse<Leaves> saveLeave(Leaves leave) {
        CommonResponse<Leaves> response = new CommonResponse<>();

        if (leave == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("leave must not be null..");
            return response;
        }

        if (leave.getLeave_id() == null) {
            if (leave.getStartDate() == null || leave.getEndDate() == null || leave.getReason() == null) {
                response.setSuccess(false);
                response.setResponseCode(HttpStatus.BAD_REQUEST.value());
                response.setResponseMessage("startDate,endDate & reason must not be null.");
                return response;
            }
            else {
                Leaves map = modelMapper.map(leave, Leaves.class);
                Leaves save = leaveRepository.save(map);

                response.setData(save);
                response.setResponseCode(HttpStatus.OK.value());
                response.setSuccess(true);
                response.setResponseMessage("Leave saved successfully.");
            }

        } else {
            if (leave.getLeave_id() != null) {
                Optional<Leaves> optional = leaveRepository.findById(leave.getLeave_id());
                if (optional.isPresent()) {
                    Leaves map = modelMapper.map(leave, Leaves.class);
                    Leaves save = leaveRepository.save(map);

                    response.setData(save);
                    response.setResponseCode(HttpStatus.OK.value());
                    response.setSuccess(true);
                    response.setResponseMessage("Leave updated successfully.");

                } else {
                    response.setSuccess(false);
                    response.setResponseCode(HttpStatus.BAD_REQUEST.value());
                    response.setResponseMessage("leave not found.");
                }
            }
        }
        return response;
    }

    @Override
    public CommonResponse<List<Leaves>> getAllLeave(Integer t_id) {

        CommonResponse<List<Leaves>> response = new CommonResponse<>();

        if (t_id == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("teacher id must not be null.");
            return response;
        }

        Optional<Teacher> optional = teacherRepository.findById(t_id);

        if (!optional.isPresent()) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("leave not found.");
            return response;
        }
        Teacher teacher = optional.get();

        if (teacher.getRole() != null && teacher.getRole().equalsIgnoreCase("principal")) {
            List<Leaves>all = leaveRepository.findAll();

            response.setSuccess(true);
            response.setResponseCode(HttpStatus.FOUND.value());
            response.setResponseMessage("Leave retrieved successfully.");
            response.setData(all);
        }

        else {
            List<Leaves>all = leaveRepository.findAllByTeacherId(t_id);
            response.setSuccess(true);
            response.setResponseCode(HttpStatus.FOUND.value());
            response.setResponseMessage("Leave retrieved successfully.");
            response.setData(all);
        }
        return response;
    }

    @Override
    public CommonResponse<Leaves> getById(Integer leave_id) {
        CommonResponse<Leaves> response = new CommonResponse<>();
        if (leave_id == null) {
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            response.setResponseMessage("leave not found.");
            return response;
        }
        Optional<Leaves> optional = leaveRepository.findById(leave_id);
        if (!optional.isPresent()) {
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            response.setResponseMessage("leave not found.");
            return response;
        }
        Leaves leave = optional.get();
        response.setSuccess(true);
        response.setResponseCode(HttpStatus.FOUND.value());
        response.setResponseMessage("leave found successfully..");
        response.setData(leave);
        return response;
    }

    @Transactional
    @Override
    public CommonResponse<Leaves> deleteByStatus(Integer leave_id) {
        CommonResponse<Leaves> response = new CommonResponse<>();

        Optional<Leaves> optional = leaveRepository.findById(leave_id);

        if (!optional.isPresent()) {
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            response.setResponseMessage("leave not found");
            return response;
        }

        Leaves leave = optional.get();
        if (leave.getStatus().equals(StatusConstant.sent)) {
            leaveRepository.deleteByStatus(StatusConstant.sent);

            response.setSuccess(true);
            response.setResponseCode(HttpStatus.FOUND.value());
            response.setResponseMessage("Leave deleted successfully.");
            response.setData(leave);
            return response;
        } else {
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            response.setResponseMessage("sent leave not found");
            return response;

        }

    }

}