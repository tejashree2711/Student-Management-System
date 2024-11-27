package com.practice.student_management.commonnotice;

import com.practice.student_management.common.CommonResponse;
import com.practice.student_management.model.CommonNotice;
import com.practice.student_management.repository.CommonNoticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommonNoticeServiceImpl implements CommonNoticeService {

    @Autowired
    CommonNoticeRepository commonNoticeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommonResponse<CommonNotice> saveNotice(CommonNotice commonNotice) {
        CommonResponse<CommonNotice> response = new CommonResponse<>();

        if (commonNotice == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("notice must not be null..");
            return response;
        }

        if (commonNotice.getDate() == null || commonNotice.getDay() == null || commonNotice.getDescription() == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("field must not be null..");
            return response;
        }

        if (commonNotice.getId() != null) {
            Optional<CommonNotice> notice = commonNoticeRepository.findById(commonNotice.getId());
            if (notice.isPresent()) {
                CommonNotice map = modelMapper.map(commonNotice, CommonNotice.class);
                CommonNotice save = commonNoticeRepository.save(map);
                response.setData(save);
                response.setResponseCode(HttpStatus.OK.value());
                response.setSuccess(true);
                response.setResponseMessage("notice updated successfully.");
            } else {
                response.setSuccess(false);
                response.setResponseCode(HttpStatus.NOT_FOUND.value());
                response.setResponseMessage("notice not found.");
            }
        } else {
            CommonNotice map = modelMapper.map(commonNotice, CommonNotice.class);
            CommonNotice save = commonNoticeRepository.save(map);
            response.setData(save);
            response.setResponseCode(HttpStatus.OK.value());
            response.setSuccess(true);
            response.setResponseMessage("notice created successfully.");
        }
        return response;
    }

    @Override
    public CommonResponse<CommonNotice> getNoticeById(Integer id) {
        CommonResponse<CommonNotice> response = new CommonResponse<>();

        Optional<CommonNotice> notice = commonNoticeRepository.findById(id);
        if (!notice.isPresent()) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("notice not found..");
        } else {
            CommonNotice notice1 = commonNoticeRepository.findByIdAndStatus(id);
            if (notice1 == null) {
                response.setSuccess(false);
                response.setResponseCode(HttpStatus.NOT_FOUND.value());
                response.setResponseMessage("Notice not found.");
            } else {
                response.setData(notice1);
                response.setResponseCode(HttpStatus.FOUND.value());
                response.setSuccess(true);
                response.setResponseMessage("notice found successfully.");
            }
        }
        return response;
    }

    @Override
    public CommonResponse<List<CommonNotice>> getAllNotice() {
        CommonResponse<List<CommonNotice>> response = new CommonResponse<>();
        List<CommonNotice> all = commonNoticeRepository.findAllByStatus();
        if (all == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("Notice not found.");
        } else {
            response.setData(all);
            response.setResponseCode(HttpStatus.FOUND.value());
            response.setSuccess(true);
            response.setResponseMessage("notice found successfully.");
        }
        return response;
    }

    @Override
    public CommonResponse<CommonNotice> deleteById(Integer id) {
        CommonResponse<CommonNotice> response = new CommonResponse<>();

        if (id == null) {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseMessage("id must not be null.");
            return response;
        }

        Optional<CommonNotice> notice = commonNoticeRepository.findById(id);
        if (notice.isPresent()) {
            CommonNotice notice1 = notice.get();
            if (notice1.getStatus() == 0) {
                response.setSuccess(false);
                response.setResponseCode(HttpStatus.BAD_REQUEST.value());
                response.setResponseMessage("notice is already deleted.");
            } else {
                notice1.setStatus(0);
                commonNoticeRepository.save(notice1);

                response.setData(notice1);
                response.setResponseCode(HttpStatus.OK.value());
                response.setSuccess(true);
                response.setResponseMessage("notice deleted successfully.");
            }
        } else {
            response.setSuccess(false);
            response.setResponseCode(HttpStatus.NOT_FOUND.value());
            response.setResponseMessage("Notice not found.");
        }
        return response;
    }
}
