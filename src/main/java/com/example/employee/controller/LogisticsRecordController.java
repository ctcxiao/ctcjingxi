package com.example.employee.controller;

import com.example.employee.entity.LogisticsRecords;
import com.example.employee.entity.ResponseLogistics;
import com.example.employee.repository.LogisticsRecordsRepository;
import com.example.employee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class LogisticsRecordController {
    @Autowired
    private LogisticsRecordsRepository logisticsRecordsRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/logisticsRecords/{id}")
    public ResponseLogistics findLogistRecords(@PathVariable("id") int id) {
        LogisticsRecords logisticsRecords = logisticsRecordsRepository.findById(id).get();

        return new ResponseLogistics(logisticsRecords.getId(), logisticsRecords.getTotalPrice(), logisticsRecords.getUserId(), logisticsRecords.getCreateTime(),
            logisticsRecords.getLogisticsStatus(), logisticsRecords.getPurchaseString());
    }

    @PutMapping(value = "/logisticsRecords/{id}/orders/{id}")
    public ResponseEntity updateLogisticsStatus(@PathVariable("id") int id, @RequestParam("logisticsStatus") String logisticsStatus) {
        if ("signed".equals(logisticsStatus)){
            orderService.signForLogistics(id);
        }
        logisticsRecordsRepository.updateLogisticsStatus(logisticsStatus, LocalDateTime.now(), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
