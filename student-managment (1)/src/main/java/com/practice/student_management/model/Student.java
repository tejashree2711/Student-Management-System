package com.practice.student_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer s_id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer roll_no;
    private String mobile_no;
    private String gender;
    private Integer age;
    private String permanent_add;
    private Integer reg_no;
    private Integer status;
    private Integer c_id;



}
