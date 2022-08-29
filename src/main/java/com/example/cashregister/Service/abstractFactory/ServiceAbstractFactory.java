package com.example.cashregister.Service.abstractFactory;


import com.example.cashregister.Service.*;

public interface ServiceAbstractFactory{
   UserService createUserService();
   ProductService createProductService();
   ReceiptService createReceiptService();
   ReportService createReportService();
   ArchiveReceiptService createArchiveReceiptService();
}

