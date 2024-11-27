package com.practice.student_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer t_id;
    private String t_name;
    private String t_email;
    private String gender;
    private String mobile_no;
    private String t_address;
    private Date join_date;
    private Integer status;
    private Integer sub_id;
    private Integer reg_no;
    private String s_name;
    private String role;

}
