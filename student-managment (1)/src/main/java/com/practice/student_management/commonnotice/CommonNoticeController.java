package com.practice.student_management.commonnotice;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.CommonNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommonNoticeController {

    @Autowired
    CommonNoticeService commonNoticeService;

    @PostMapping("saveNotice")
    public CommonResponse<CommonNotice> saveNotice(@RequestBody CommonNotice commonNotice) {
        return commonNoticeService.saveNotice(commonNotice);
    }

    @GetMapping("getNoticeById")
    public CommonResponse<CommonNotice> getNoticeById(@RequestParam Integer id) {
        return commonNoticeService.getNoticeById(id);
    }

    @GetMapping("getAllNotice")
    public CommonResponse<List<CommonNotice>> getAllNotice() {
        return commonNoticeService.getAllNotice();
    }

    @DeleteMapping("deleteById")
    public CommonResponse<CommonNotice> deleteById(@RequestParam Integer id) {
        return commonNoticeService.deleteById(id);
    }
}
