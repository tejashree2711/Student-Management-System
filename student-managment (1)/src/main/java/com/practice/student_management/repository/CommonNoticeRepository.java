package com.practice.student_management.repository;

import com.practice.student_management.model.CommonNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommonNoticeRepository extends JpaRepository<CommonNotice,Integer> {
    @Query(
            value = "SELECT * FROM commonnotice WHERE id = :id and status =1",
            nativeQuery = true
    )
    CommonNotice findByIdAndStatus(Integer id);

    @Query(
            value = "SELECT * FROM commonnotice WHERE status =1",
            nativeQuery = true
    )
    List<CommonNotice> findAllByStatus();
}
