package com.practice.student_management.common;

import lombok.Data;

@Data
public class CommonResponse<S> {
    Boolean success;
    Integer responseCode;
    String responseMessage;
    Object data;
}
