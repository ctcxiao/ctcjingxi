package com.example.employee.repository;

import com.example.employee.entity.LogisticsRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface LogisticsRecordsRepository extends JpaRepository<LogisticsRecords, Integer> {

    @Modifying
    @Transactional
    @Query("update LogisticsRecords set logisticsStatus=?1, createTime=?2 where id=?3")
    void updateLogisticsStatus(String logisticsStatus, LocalDateTime time, int id);

}
