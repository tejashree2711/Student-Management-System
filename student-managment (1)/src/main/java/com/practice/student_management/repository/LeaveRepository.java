package com.practice.student_management.repository;

import com.practice.student_management.model.Leaves;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leaves, Integer> {

    void deleteByStatus(String status);

    @Query(
            value = "SELECT * FROM leaves WHERE t_id = :t_id",
            nativeQuery = true
    )
    List<Leaves> findAllByTeacherId(Integer t_id);

}
