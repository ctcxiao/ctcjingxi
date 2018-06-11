package com.example.employee.repository;

import com.example.employee.entity.LogisticsRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LogisticsRecordsRepository extends JpaRepository<LogisticsRecords, Integer> {

    @Modifying
    @Transactional
    @Query("update LogisticsRecords set logisticsStatus=?1 where id=?2")
    void updateLogisticsStatus(String logisticsStatus, int id);

}
