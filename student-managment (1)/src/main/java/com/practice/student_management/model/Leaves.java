package com.practice.student_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Leaves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leave_id;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private Integer approval_id;
    private Integer t_id;
}
