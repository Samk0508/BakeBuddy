package com.bakebuddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.BakeryOwnerReport;
import com.bakebuddy.repository.BakeryOwnerReportRepository;
import com.bakebuddy.service.BakeryOwnerReportService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BakeryOwnerReportServiceImpl implements BakeryOwnerReportService {

    @Autowired
    private BakeryOwnerReportRepository bakeryOwnerReportRepository;


    @Override
    public BakeryOwnerReport getBakeryOwnerReport(BakeryOwner bakeryOwner) {
    	BakeryOwnerReport report = bakeryOwnerReportRepository.findByBakeryOwnerId(bakeryOwner.getId());
        if(report == null){
        	BakeryOwnerReport newReport = new BakeryOwnerReport();
            newReport.setBakeryOwner(bakeryOwner);
            return bakeryOwnerReportRepository.save(newReport);
        }
        return report;
    }


    @Override
    public BakeryOwnerReport updateBakeryOwnerReport(BakeryOwnerReport bakeryOwnerReport) {

        return bakeryOwnerReportRepository.save(bakeryOwnerReport);
    }


	
}
