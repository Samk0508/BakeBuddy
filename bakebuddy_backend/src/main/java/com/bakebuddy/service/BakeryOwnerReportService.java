package com.bakebuddy.service;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.BakeryOwnerReport;

public interface BakeryOwnerReportService {
	BakeryOwnerReport getBakeryOwnerReport(BakeryOwner bakeryOwner);
	BakeryOwnerReport updateBakeryOwnerReport( BakeryOwnerReport BakeryOwnerReport);

}
