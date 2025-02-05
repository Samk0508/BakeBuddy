package com.bakebuddy.service;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.BakeryOwnerReport;

public interface BakeryOwnerReportService {
	BakeryOwnerReport getSellerReport(BakeryOwner bakeryOwner);
	BakeryOwnerReport updateSellerReport( BakeryOwnerReport BakeryOwnerReport);

}
