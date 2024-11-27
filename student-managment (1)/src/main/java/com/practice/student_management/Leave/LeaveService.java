package com.practice.student_management.Leave;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.Leaves;

import java.util.List;

public interface LeaveService {
    public CommonResponse<Leaves> saveLeave(Leaves leave);

    public CommonResponse<List<Leaves>> getAllLeave(Integer t_id);

    public CommonResponse<Leaves> getById(Integer leave_id);

    public CommonResponse<Leaves> deleteByStatus(Integer leave_id);

}
