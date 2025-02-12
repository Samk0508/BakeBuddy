package com.bakebuddy.service;

import java.util.List;
import java.util.Optional;

import com.bakebuddy.model.Seller;
import com.bakebuddy.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport( SellerReport sellerReport);

}
