package com.practice.student_management.Leave;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Leaves;
import com.practice.student_management.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    @Autowired
    LeaveRepository leaveRepository;

    @PostMapping("applyleave")
    public CommonResponse<Leaves> saveLeave(@RequestBody Leaves leave) {
        return leaveService.saveLeave(leave);
    }

    @GetMapping("getAllLeave")
    public CommonResponse<List<Leaves>> getAllLeave(@RequestParam Integer id) {
        return leaveService.getAllLeave(id);
    }

    @GetMapping("getById")
    public CommonResponse<Leaves> getById(@RequestParam Integer leave_id) {
        return leaveService.getById(leave_id);
    }

    @DeleteMapping("deleteByStatus")
    public CommonResponse<Leaves> deleteByStatus(@RequestParam Integer leave_id) {
        return leaveService.deleteByStatus(leave_id);
    }


}