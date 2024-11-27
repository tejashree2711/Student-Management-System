package com.practice.student_management.commonnotice;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.CommonNotice;

import java.util.List;

public interface CommonNoticeService {
    CommonResponse<CommonNotice> saveNotice(CommonNotice commonNotice);
    CommonResponse<CommonNotice> getNoticeById(Integer id);
    CommonResponse<List<CommonNotice>> getAllNotice();
    CommonResponse<CommonNotice> deleteById(Integer id);
}
