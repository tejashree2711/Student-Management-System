package com.practice.student_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
@Entity
@Data
@Table(name = "commonnotice")
public class CommonNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer status;
    private Date date;
    private String day;
    private Timestamp time;
}
